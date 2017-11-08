package net.netty.server.startup;

import net.netty.server.thread.ProtobufServer;

public class PotobufServerSetup {
	public static void main(String[] args) {
		new Thread(new ProtobufServer()).start();
	}
}
