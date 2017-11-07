package net.netty.client.startup;

import net.netty.client.thread.NoStrickyClient;

public class NioStrickyBagClientSetup {
	public static void main(String[] args) {
		new Thread(new NoStrickyClient()).start();
	}
}
