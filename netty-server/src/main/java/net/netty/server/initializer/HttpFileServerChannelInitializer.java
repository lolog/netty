package net.netty.server.initializer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import net.netty.server.handler.HttpFileServerHandler;


public class HttpFileServerChannelInitializer extends ChannelInitializer<SocketChannel> {
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast("http-decoder", new HttpRequestDecoder());
		pipeline.addLast("http-aggregator",new HttpObjectAggregator(65536));
		pipeline.addLast("http-encoder",new HttpResponseEncoder());
		pipeline.addLast("http-chunked",new ChunkedWriteHandler());
		pipeline.addLast("fileServerHandler", new HttpFileServerHandler());
	}
}
