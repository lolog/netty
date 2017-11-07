package net.netty.server.handler;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

import net.netty.server.thread.AIOAsyncServer;

public class AIOAsyncAcceptHandler implements CompletionHandler<AsynchronousSocketChannel, AIOAsyncServer>{
	
	public void completed(AsynchronousSocketChannel result, AIOAsyncServer attachment) {
		// agian
		attachment.getChannel().accept(attachment, this);
		
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		result.read(buffer, buffer, new AIOReadHandler(result));
	}

	public void failed(Throwable exc, AIOAsyncServer attachment) {
		attachment.getLatch().countDown();
	}

}
