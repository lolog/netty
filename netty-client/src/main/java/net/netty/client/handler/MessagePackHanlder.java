package net.netty.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import net.netty.plugins.pojo.People;

public class MessagePackHanlder extends ChannelInboundHandlerAdapter {
	private int number;
	public MessagePackHanlder(int number) {
		this.number = number;
	}
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("[Client] ----> send message...");
		People[] peoples = createPeoples();
		for(People people: peoples) {
			ctx.write(people);
		}
		ctx.flush();
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("[Client receive] ----> " + msg);
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
	
	private People[] createPeoples () {
		People[] peoples = new People[number];
		
		for (int i = 0; i < number; i++) {
			People people = new People();
			people.setName("people_" + i);
			people.setAge(15+i);
			people.setInfo("info_" + i);
			
			peoples[i] = people;
		}
		
		return peoples;
	}
}
