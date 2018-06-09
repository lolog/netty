package net.netty.server.thread;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.text.MessageFormat;

import net.netty.server.handler.WebSocketServerHandler;

public class WebSocketServer implements Runnable {
	private int port;
	
	public WebSocketServer(int port) {
		this.port = port;
	}
	
	@Override
	public void run() {
		EventLoopGroup boosGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		ServerBootstrap server = new ServerBootstrap();
		server.group(boosGroup, workerGroup)
			  .channel(NioServerSocketChannel.class)
			  .childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ChannelPipeline pipeline = ch.pipeline();
					// 将请求/应答消息编码或者解码为HTTP消息
					pipeline.addLast("http-codec", new HttpServerCodec());
					// 将HTTP消息的多个部分组合成一条完整的HTTP消息
					pipeline.addLast("aggregator", new HttpObjectAggregator(65536));
					// 支持浏览器和服务端进行WebSocket通信
					pipeline.addLast("http-chunked", new ChunkedWriteHandler());
					pipeline.addLast("websockt-handler", new WebSocketServerHandler());
				}
			});
		try {
			Channel channel = server.bind(port).sync().channel();
			System.out.println(MessageFormat.format("WebSocket(Port={0}) Started", port));
			channel.closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			boosGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
