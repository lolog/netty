package net.netty.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class DelimiterDecoderHandler extends ChannelInboundHandlerAdapter {
	private int counter;
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String body = (String) msg;
		
		System.out.println("[counter]="+ ++counter + "; [client]=" + body);
		
		body += "$_";
		
		ByteBuf resp = Unpooled.copiedBuffer(body.getBytes());
		ctx.writeAndFlush(resp);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		super.exceptionCaught(ctx, cause);
		ctx.close();
	}
}
