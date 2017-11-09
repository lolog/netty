package net.netty.client.thread;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import net.netty.client.handler.JBossMarshallingClientHanlder;
import net.netty.plugins.decoder.marshalling.MarshallingCustomingDecoder;
import net.netty.plugins.encoder.marshalling.MarshallingCustomingEncoder;

public class JBossMarshallingClient implements Runnable {
	private String host;
	private int port;
	
	public JBossMarshallingClient() {
		this(8899);
	}
	
	public JBossMarshallingClient(int port) {
		this("127.0.0.1", port);
	}
	
	public JBossMarshallingClient(String host, int port) {
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
					ch.pipeline().addLast(
							MarshallingCustomingDecoder.buildDecoder(),
							MarshallingCustomingEncoder.buildEncoder(),
							
							new JBossMarshallingClientHanlder()
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
