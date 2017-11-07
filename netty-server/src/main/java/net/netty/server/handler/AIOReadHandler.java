package net.netty.server.handler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AIOReadHandler implements CompletionHandler<Integer, ByteBuffer>{
	AsynchronousSocketChannel channel;
	
	public AIOReadHandler(AsynchronousSocketChannel channel) {
		this.channel = channel;
	}
	
	public void completed(Integer result, ByteBuffer attachment) {
		attachment.flip();
		byte[] body = new byte[attachment.remaining()];
		attachment.get(body);
		
		try {
			String requestBody = new String(body, "UTF-8");
			
			System.out.println("[server] receive = " + requestBody);
			
			String response = "[response] addr="+ channel.getLocalAddress();
			
			System.out.println("[server] will response = " + response);
			
			// response
			final ByteBuffer writeBuffer = ByteBuffer.allocate(response.length());
			writeBuffer.put(response.getBytes());
			writeBuffer.flip();
			
			channel.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {
				public void completed(Integer result, ByteBuffer attachment) {
					if (attachment.hasRemaining()) {
						channel.write(writeBuffer, writeBuffer, this);
					}
					else {
						System.out.println("[server] reponse finished...");
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
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void failed(Throwable exc, ByteBuffer attachment) {
		
	}

}
