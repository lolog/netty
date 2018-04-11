package net.netty.server.startup;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import net.netty.server.initializer.HttpFileServerChannelInitializer;

public class HttpFileServerSetup {
	public static void main(String[] args) throws InterruptedException {
		EventLoopGroup boosGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try {
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(boosGroup, workerGroup)
						   .channel(NioServerSocketChannel.class)
						   .handler(new LoggingHandler(LogLevel.DEBUG))
						   .childHandler(new HttpFileServerChannelInitializer());
			
			ChannelFuture future = serverBootstrap.bind(8899);
			future.channel().closeFuture().sync();
		} finally {
			boosGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
