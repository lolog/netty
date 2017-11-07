package net.netty.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import net.netty.client.pojo.UserInfo;

public class MessagePackHanlder extends ChannelInboundHandlerAdapter {
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("[Client] ----> send message...");
		UserInfo[] userInfos = createUserInfo();
		for(UserInfo userInfo: userInfos) {
			ctx.write(userInfo);
		}
		ctx.flush();
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("[Client receive] ----> " + msg);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}
	
	private UserInfo[] createUserInfo () {
		UserInfo[] userInfos = new UserInfo[10];
		
		for (int i = 0; i < 3; i++) {
			UserInfo userInfo = new UserInfo();
			userInfo.setName("user_" + i);
			userInfo.setAge(15+i);
			userInfo.setCompany("company_" + i);
			userInfo.setInfo("info_" + i);
			
			userInfos[i] = userInfo;
		}
		
		return userInfos;
	}
}
