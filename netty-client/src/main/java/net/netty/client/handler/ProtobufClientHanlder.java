package net.netty.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import net.netty.plugins.pojo.User;

public class ProtobufClientHanlder extends ChannelInboundHandlerAdapter {
	public ProtobufClientHanlder() {
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
	
	private User.UserInfo createUserInfo (int i) {
		User.UserInfo.Builder builder = User.UserInfo.newBuilder();
		
		builder.setName("name_" + i);
		builder.setAge(15 + i);
		builder.setInfo("info_" + i);
		
		return builder.build();
	}
}
