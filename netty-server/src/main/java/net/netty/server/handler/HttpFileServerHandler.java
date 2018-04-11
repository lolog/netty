package net.netty.server.handler;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.util.CharsetUtil;

import java.io.File;

public class HttpFileServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
	@Override
	protected void messageReceived(ChannelHandlerContext ctx, FullHttpRequest request)
			throws Exception {
		if (request.getDecoderResult().isFailure()) {
			sendError(ctx, HttpResponseStatus.BAD_REQUEST);
			return;
		}
		if (request.getMethod() != HttpMethod.GET) {
			sendError(ctx, HttpResponseStatus.METHOD_NOT_ALLOWED);
		}
		
		String url = request.getUri();
		String filePath = url.substring(url.lastIndexOf("=") + 1);
		
		File dir = new File(filePath);
		if (dir.exists() && dir.isDirectory()) {
			sendDir(ctx, dir);
		}
		sendError(ctx, HttpResponseStatus.FORBIDDEN);
	}

	private void sendError(ChannelHandlerContext ctx, HttpResponseStatus status) {
		FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, status, Unpooled.copiedBuffer("Failure: " + status + "\r\n", CharsetUtil.UTF_8));
		response.headers().set(CONTENT_TYPE, "text/plain; charset=UTF-8");
		ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
	}
	private void sendDir(ChannelHandlerContext ctx, File dir) {
		FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, HttpResponseStatus.OK);
		response.headers().set(CONTENT_TYPE, "text/html; charset=UTF-8");

		StringBuilder buf = new StringBuilder();
		String dirPath = dir.getPath();

		buf.append("<!DOCTYPE html>");
		buf.append("<html><head><title>");
		buf.append("Listing of: ");
		buf.append(dirPath);
		buf.append("</title></head><body>");

		buf.append("<h3>Listing of: ");
		buf.append(dirPath);
		buf.append("</h3>");

		buf.append("<ul>");
		buf.append("<li><a href=\"/?file="+ dir.getAbsolutePath().substring(0, dir.getAbsolutePath().lastIndexOf(File.separator)) + File.separator + "\">..</a></li>");

		for (File file : dir.listFiles()) {
			if (file.isHidden() || !file.canRead()) {
				continue;
			}

			String name = file.getName();
			
			buf.append("<li><a href=\"");
			buf.append("/?file="+file.getAbsolutePath());
			buf.append("\">");
			buf.append(name);
			buf.append("</a></li>");
		}

		buf.append("</ul></body></html>");
		ByteBuf buffer = Unpooled.copiedBuffer(buf, CharsetUtil.UTF_8);
		response.content().writeBytes(buffer);
		buffer.release();

		// Close the connection as soon as the error message is sent.
		ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
	}
}
