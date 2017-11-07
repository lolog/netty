package net.netty.server.thread;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.util.concurrent.atomic.AtomicBoolean;

import net.netty.server.handler.NoStrickyBagHandler;

public class NoStrickyBagServer implements Runnable {
	int port;
	
	ServerBootstrap server;
	
	// Server Nio Group
	EventLoopGroup boosGroup = new NioEventLoopGroup();
	EventLoopGroup workGroup = new NioEventLoopGroup();
	
	volatile AtomicBoolean start = new AtomicBoolean(false);
	
	public NoStrickyBagServer() {
		this(8899);
	}
	
	public NoStrickyBagServer(int port) {
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
					// stricky bag
					new LineBasedFrameDecoder(1024),
					// new FixedLengthFrameDecoder(2),
					new StringDecoder(),
					//
					
					new NoStrickyBagHandler()
			);
		}
	}
}


