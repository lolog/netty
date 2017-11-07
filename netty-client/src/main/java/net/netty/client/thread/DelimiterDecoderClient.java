package net.netty.client.thread;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import net.netty.client.handler.DelimiterDecoderClientHanlder;

public class DelimiterDecoderClient implements Runnable {
	private String host;
	private int port;
	
	public DelimiterDecoderClient() {
		this(8899);
	}
	
	public DelimiterDecoderClient(int port) {
		this("127.0.0.1", port);
	}
	
	public DelimiterDecoderClient(String host, int port) {
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
					ByteBuf buf = Unpooled.copiedBuffer("$_".getBytes());
					
					ch.pipeline().addLast(
							// delimiter stricky bag
							new DelimiterBasedFrameDecoder(1024, buf),
							new StringDecoder(),
							new DelimiterDecoderClientHanlder()
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
