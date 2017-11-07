package net.netty.server.thread;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

import net.netty.server.handler.AIOAsyncAcceptHandler;

public class AIOAsyncServer implements Runnable{
	int port;
	String addr;
	
	CountDownLatch latch;
	AsynchronousServerSocketChannel channel;
	
	public AIOAsyncServer() throws IOException {
		this("127.0.0.1", 8099);
	}
	
	public AIOAsyncServer(int port) throws IOException {
		this("127.0.0.1", port);
	}
	
	public AIOAsyncServer(String addr, int port) throws IOException {
		this.port = port;
		this.addr = addr;
		
		this.channel = AsynchronousServerSocketChannel.open();
		this.channel.bind(new InetSocketAddress(addr, port));
		
		System.out.println("[server] start, host=" + addr + ", port=" + port);
	}
	
	public void run() {
		latch = new CountDownLatch(1);
		
		// connected action
		doConnected();
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void doConnected () {
		this.channel.accept(this, new AIOAsyncAcceptHandler());
	}
	
	public AsynchronousServerSocketChannel getChannel() {
		return channel;
	}
	public CountDownLatch getLatch() {
		return latch;
	}
}
