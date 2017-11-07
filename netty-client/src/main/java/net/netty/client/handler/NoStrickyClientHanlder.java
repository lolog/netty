package net.netty.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NoStrickyClientHanlder extends ChannelInboundHandlerAdapter {
	private int counter;
	private byte[] req;
	
	public NoStrickyClientHanlder() {
		req = ("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ByteBuf message = null;
		for(int i=0; i<100; i++) {
			message = Unpooled.buffer(req.length);
			message.writeBytes(req);
			ctx.writeAndFlush(message);
		}
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String body = (String) msg;
		
		System.out.println("Now is =" + body + " ;the counter is=" + ++counter);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}
}
