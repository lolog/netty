package net.netty.client.startup;

import net.netty.client.thread.ProtobufClient;

public class ProtobufClientSetup {
	public static void main(String[] args) {
		new Thread(new ProtobufClient()).start();
	}
}
