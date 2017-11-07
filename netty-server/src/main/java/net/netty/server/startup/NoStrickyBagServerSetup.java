package net.netty.server.startup;

import net.netty.server.thread.NoStrickyBagServer;

public class NoStrickyBagServerSetup {
	public static void main(String[] args) {
		new Thread(new NoStrickyBagServer()).start();
	}
}
