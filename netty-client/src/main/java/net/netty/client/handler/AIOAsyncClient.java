package net.netty.client.handler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

public class AIOAsyncClient implements CompletionHandler<Void, AIOAsyncClient>,  Runnable{
	AsynchronousSocketChannel channel;
	String addr;
	int port;
	
	CountDownLatch latch;
	
	public AIOAsyncClient() throws IOException {
		this(8099);
	}
	
	public AIOAsyncClient(int port) throws IOException {
		this("127.0.0.1", port);
	}
	
	public AIOAsyncClient(String addr, int port) throws IOException {
		this.addr = addr;
		this.port = port;
		
		this.latch = new CountDownLatch(1);
		this.channel = AsynchronousSocketChannel.open();
	}
	
	public void run() {
		channel.connect(new InetSocketAddress(addr, port), this, this);
		System.out.println("[client] connected, host=" + addr + ", port=" + port);
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void completed(Void result, AIOAsyncClient attachment) {
		String post = "my name is Client";
		final ByteBuffer writeBuffer = ByteBuffer.allocate(post.length());
		writeBuffer.put(post.getBytes());
		writeBuffer.flip();
		
		System.out.println("[client] will send body = " + post);
		
		channel.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {
			public void completed(Integer result, ByteBuffer attachment) {
				if (attachment.hasRemaining()) {
					channel.write(writeBuffer, writeBuffer, this);
				}
				else {
					ByteBuffer readBuffer = ByteBuffer.allocate(1024);
					channel.read(readBuffer, readBuffer, new CompletionHandler<Integer, ByteBuffer>() {
						public void completed(Integer result, ByteBuffer attachment) {
							attachment.flip();
							byte[] body = new byte[attachment.remaining()];
							attachment.get(body);
							
							String bodyContent;
							try {
								bodyContent = new String(body, "UTF-8");
								System.out.println("[client] response = " + bodyContent);
							} catch (UnsupportedEncodingException e) {
								e.printStackTrace();
							}
						}

						public void failed(Throwable exc, ByteBuffer attachment) {
							try {
								channel.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					});
				}
			}

			public void failed(Throwable exc, ByteBuffer attachment) {
				try {
					channel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void failed(Throwable exc, AIOAsyncClient attachment) {
		
	}

}
