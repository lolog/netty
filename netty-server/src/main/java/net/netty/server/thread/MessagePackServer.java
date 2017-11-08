package net.netty.server.thread;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

import java.util.concurrent.atomic.AtomicBoolean;

import net.netty.plugins.decoder.message.pack.MessagePackDecoder;
import net.netty.plugins.encoder.message.pack.MessagePackEncoder;
import net.netty.server.handler.MessagePackHandler;

public class MessagePackServer implements Runnable {
	int port;
	
	ServerBootstrap server;
	
	// Server Nio Group
	EventLoopGroup boosGroup = new NioEventLoopGroup();
	EventLoopGroup workGroup = new NioEventLoopGroup();
	
	volatile AtomicBoolean start = new AtomicBoolean(false);
	
	public MessagePackServer() {
		this(8899);
	}
	
	public MessagePackServer(int port) {
		this.port = port;
	}
	
	
	public void run() {
		if(start.compareAndSet(false, true)) {
			server = new ServerBootstrap();
			
			server.group(boosGroup, workGroup)
				.channel(NioServerSocketChannel.class)
				.option(ChannelOption.SO_BACKLOG, 1024)
				.childHandler(new ChildChannelHandler());
			
			System.out.println("[Info] server --> localhost:" + this.port + " started...");
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
			// stricky bag
			ch.pipeline().addLast("frameDecoder", new LengthFieldBasedFrameDecoder(65536, 0, 2, 0, 2));
			ch.pipeline().addLast("decoder", new MessagePackDecoder());
			// stricky bag
			ch.pipeline().addLast("frameEncoder", new LengthFieldPrepender(2));
			ch.pipeline().addLast("encoder", new MessagePackEncoder());
			
			ch.pipeline().addLast(
					new MessagePackHandler()
			);
		}
	}
}


