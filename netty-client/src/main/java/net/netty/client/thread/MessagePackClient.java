package net.netty.client.thread;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

import net.netty.client.handler.MessagePackHanlder;
import net.netty.plugins.message.pack.decoder.MessagePackDecoder;
import net.netty.plugins.message.pack.encoder.MessagePackEncoder;

public class MessagePackClient implements Runnable {
	private String host;
	private int port;
	
	public MessagePackClient() {
		this(8899);
	}
	
	public MessagePackClient(int port) {
		this("127.0.0.1", port);
	}
	
	public MessagePackClient(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	EventLoopGroup group = new NioEventLoopGroup();
	Bootstrap client = new Bootstrap();
	
	public void run() {
		client.group(group)
			.channel(NioSocketChannel.class)
			.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast("decoder", new MessagePackDecoder());
					ch.pipeline().addLast("encoder", new MessagePackEncoder());
					
					ch.pipeline().addLast(
							new MessagePackHanlder()
					);
				}
			});
		ChannelFuture future;
		try {
			future = client.connect(new InetSocketAddress(this.host, this.port)).sync();
			// waite client channel close
			future.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		finally {
			group.shutdownGracefully();
		}
		
	}
}
