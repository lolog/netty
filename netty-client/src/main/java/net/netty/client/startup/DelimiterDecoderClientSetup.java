package net.netty.client.startup;

import net.netty.client.thread.DelimiterDecoderClient;

public class DelimiterDecoderClientSetup {
	public static void main(String[] args) {
		new Thread(new DelimiterDecoderClient()).start();
	}
}
