package net.netty.client.thread;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.net.InetSocketAddress;

import net.netty.client.handler.NoStrickyClientHanlder;

public class NoStrickyClient implements Runnable {
	private String host;
	private int port;
	
	public NoStrickyClient() {
		this(8899);
	}
	
	public NoStrickyClient(int port) {
		this("127.0.0.1", port);
	}
	
	public NoStrickyClient(String host, int port) {
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
							// stricky bag
							new LineBasedFrameDecoder(1024),
							// new FixedLengthFrameDecoder(2),
							new StringDecoder(),
							//
							
							new NoStrickyClientHanlder()
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
