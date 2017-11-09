package net.netty.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import net.netty.plugins.pojo.People;

public class JBossMarshallingClientHanlder extends ChannelInboundHandlerAdapter {
	public JBossMarshallingClientHanlder() {
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		for (int i=0; i<10; i++) {
			ctx.write(createUserInfo(i));
		}
		ctx.flush();
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("[client receive] " + msg);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}
	
	private People createUserInfo (int i) {
		People people = new People();
		
		people.setName("name_" + i);
		people.setAge(15 + i);
		people.setInfo("info_" + i);
		
		return people;
	}
}
