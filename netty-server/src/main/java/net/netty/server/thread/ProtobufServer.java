package net.netty.server.thread;

import java.util.concurrent.atomic.AtomicBoolean;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import net.netty.plugins.pojo.User;
import net.netty.server.handler.ProtobufHandler;

public class ProtobufServer implements Runnable {
	int port;
	
	ServerBootstrap server;
	
	// Server Nio Group
	EventLoopGroup boosGroup = new NioEventLoopGroup();
	EventLoopGroup workGroup = new NioEventLoopGroup();
	
	volatile AtomicBoolean start = new AtomicBoolean(false);
	
	public ProtobufServer() {
		this(8899);
	}
	
	public ProtobufServer(int port) {
		this.port = port;
	}
	
	
	public void run() {
		if(start.compareAndSet(false, true)) {
			server = new ServerBootstrap();
			
			server.group(boosGroup, workGroup)
				.channel(NioServerSocketChannel.class)
				.option(ChannelOption.SO_BACKLOG, 1024)
				.childHandler(new ChildChannelHandler());
			
			try {
				ChannelFuture future = server.bind(this.port).sync();
				future.channel().closeFuture().sync();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			finally {
				boosGroup.shutdownGracefully();
				workGroup.shutdownGracefully();
			}
		}
	}
	
	private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
		@Override
		protected void initChannel(SocketChannel ch) throws Exception {
			ch.pipeline().addLast(
					new ProtobufVarint32FrameDecoder(),
					new ProtobufDecoder(User.UserInfo.getDefaultInstance()),
					
					new ProtobufVarint32LengthFieldPrepender(),
					new ProtobufEncoder(),
					//
					
					new ProtobufHandler()
			);
		}
	}
}


