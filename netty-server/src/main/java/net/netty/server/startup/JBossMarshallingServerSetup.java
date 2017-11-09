package net.netty.server.startup;

import net.netty.server.thread.JBossMarshallingServer;

public class JBossMarshallingServerSetup {
	public static void main(String[] args) {
		new Thread(new JBossMarshallingServer()).start();
	}
}
