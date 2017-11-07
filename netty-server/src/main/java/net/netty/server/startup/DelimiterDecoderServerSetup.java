package net.netty.server.startup;

import net.netty.server.thread.DelimiterDecoderServer;

public class DelimiterDecoderServerSetup {
	public static void main(String[] args) {
		new Thread(new DelimiterDecoderServer()).start();
	}
}
