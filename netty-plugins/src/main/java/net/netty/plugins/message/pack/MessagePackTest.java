package net.netty.plugins.message.pack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.msgpack.MessagePack;
import org.msgpack.template.Templates;

public class MessagePackTest {
	
	@Test
	public void apiSimple() throws IOException {
		List<String> src = new ArrayList<String>();
		src.add("messagePack");
		src.add("lolog");
		
		MessagePack messagePack = new MessagePack();
		byte[] raws = messagePack.write(src);
		
		List<String> dests = messagePack.read(raws, Templates.tList(Templates.TString));
		
		for (String dest: dests) {
			System.out.println(dest);
		}
	}
}
