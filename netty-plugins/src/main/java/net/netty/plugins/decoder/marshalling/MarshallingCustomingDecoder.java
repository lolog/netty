package net.netty.plugins.decoder.marshalling;

import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

import io.netty.handler.codec.marshalling.DefaultUnmarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallingDecoder;
import io.netty.handler.codec.marshalling.UnmarshallerProvider;

public class MarshallingCustomingDecoder {
	public static MarshallingDecoder buildDecoder () {
		final MarshallerFactory factory = Marshalling.getProvidedMarshallerFactory("serial");
		final MarshallingConfiguration config = new MarshallingConfiguration();
		
		config.setVersion(5);
		UnmarshallerProvider provider = new DefaultUnmarshallerProvider(factory, config);
		
		return new MarshallingDecoder(provider);
	}
}
