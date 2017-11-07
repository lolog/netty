package net.netty.server.startup;

import net.netty.server.thread.MessagePackServer;

public class MessagePackServerSetup {
	public static void main(String[] args) {
		new Thread(new MessagePackServer()).start();
	}
}
