package net.netty.client.startup;

import net.netty.client.thread.JBossMarshallingClient;

public class JBossMarshallingClientSetup {
	public static void main(String[] args) {
		new Thread(new JBossMarshallingClient()).start();
	}
}
