package net.netty.server.startup;

import net.netty.server.thread.WebSocketServer;

public class WebSocketServerSetup {
	public static void main(String[] args) {
		Thread thread = new Thread(new WebSocketServer(8080));
		thread.start();
	}
}
