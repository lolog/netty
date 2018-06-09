package net.netty.server.handler;

import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.CharsetUtil;

import java.text.MessageFormat;
import java.util.Date;

public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {
	private WebSocketServerHandshaker handshaker;
	
	@Override
	protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
		// 普通的HTTP请求
		if (msg instanceof FullHttpRequest) {
			handleHttpRequest(ctx, (FullHttpRequest) msg);
		}
		
		// WebSocket
		if(msg instanceof WebSocketFrame) {
			WebSocketFrame frame = (WebSocketFrame) msg;
			handleWebSocketFrame(ctx, frame);
		}
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}
	
	/**
	 * 处理Http请求
	 * @param ctx Context
	 * @param req 请求
	 */
	public void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request) {
		if (request.getDecoderResult().isFailure() 
			|| "WebSocket".equalsIgnoreCase(request.headers().get("Upgrade")) == false) {
			DefaultFullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, BAD_REQUEST);
			sendHttpResponse(ctx, request, response);
		}
		
		String webSocketURL = "ws://localhost:8080/websocket";
		// 构件握手工厂, 创建握手类对象, 通过握手对象,响应消息返回给客户端。
		WebSocketServerHandshakerFactory shakerFactory = new WebSocketServerHandshakerFactory(webSocketURL, null, false);
		handshaker = shakerFactory.newHandshaker(request);
		
		if(handshaker == null) {
			WebSocketServerHandshakerFactory.sendUnsupportedWebSocketVersionResponse(ctx.channel());
		}
		else {
			handshaker.handshake(ctx.channel(), request);
		}
	}
	
	/**
	 * 发送Http响应
	 * @param ctx Context
	 * @param req 请求
	 * @param res 响应
	 */
	@SuppressWarnings("static-access")
	public void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest request, FullHttpResponse response) {
		if(response.getStatus().code() != 200) {
			ByteBuf buf = Unpooled.copiedBuffer(response.getStatus().toString(), CharsetUtil.UTF_8);
			response.content().writeBytes(buf);
			buf.release();
			response.headers().setContentLength(response, response.content().readableBytes());
		}
		
		// 非Keep-Alive, 关闭链接
		ChannelFuture future = ctx.channel().writeAndFlush(response);
		if (request.headers().isKeepAlive(request) == false
			|| response.getStatus().code() != 200) {
			future.addListener(ChannelFutureListener.CLOSE);
		}
	}
	
	/**
	 * 处理WebSocket请求
	 * @param ctx
	 * @param frame
	 */
	public void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
		WebSocketFrame socketFrame = frame.retain();
		// 是否关闭链路
		if (frame instanceof CloseWebSocketFrame) {
			handshaker.close(ctx.channel(), (CloseWebSocketFrame) socketFrame);
			return;
		}
		// Ping消息
		if (frame instanceof PingWebSocketFrame) {
			ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
			return;
		}
		// 仅支持文本消息
		if (frame instanceof TextWebSocketFrame == false) {
			throw new UnsupportedOperationException(MessageFormat.format("{0} Frame Types No Types", frame.getClass().getName()));
		}
		
		String requestText = ((TextWebSocketFrame) frame).text();
		ctx.channel().write(new TextWebSocketFrame(requestText + ", Welcome to Netty WebSocket, Now Date=" + new Date()));
	}
}
