package net.netty.plugins.decoder.message.pack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

import org.msgpack.MessagePack;

public class MessagePackDecoder extends MessageToMessageDecoder<ByteBuf> {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf msg,
			List<Object> out) throws Exception {
		final int length = msg.readableBytes();
		final byte[] arrays = new byte[length];
		
		msg.getBytes(msg.readerIndex(), arrays, 0, length);
		
		MessagePack messagePack = new MessagePack();
		out.add(messagePack.read(arrays));
	}

}
