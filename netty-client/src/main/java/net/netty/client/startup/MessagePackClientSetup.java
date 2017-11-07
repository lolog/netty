package net.netty.client.startup;

import net.netty.client.thread.MessagePackClient;

public class MessagePackClientSetup {
	public static void main(String[] args) {
		new Thread(new MessagePackClient()).start();
	}
}
