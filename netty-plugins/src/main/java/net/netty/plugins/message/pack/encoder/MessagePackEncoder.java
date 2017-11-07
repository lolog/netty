package net.netty.plugins.message.pack.encoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import org.msgpack.MessagePack;

public class MessagePackEncoder extends MessageToByteEncoder<Object>{

	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out)
			throws Exception {
		MessagePack messagePack = new MessagePack();
		
		// serialize
		byte[] raws = messagePack.write(msg);
		out.writeBytes(raws);
	}

}
