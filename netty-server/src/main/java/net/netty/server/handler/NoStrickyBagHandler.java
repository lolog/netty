package net.netty.server.handler;

import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NoStrickyBagHandler extends ChannelInboundHandlerAdapter {
	private int counter;
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String body = (String) msg;
		
		System.out.println("The time server receiver order=" + body + " ;the counter is="+ counter);
		
		String currentTime = "QUERY TIME ORDER".equals(body) ? new Date() .toString() : "BAD ORDER";
		
		currentTime += System.getProperty("line.separator");
		
		ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
		
		ctx.writeAndFlush(resp);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		super.exceptionCaught(ctx, cause);
		ctx.close();
	}
}
