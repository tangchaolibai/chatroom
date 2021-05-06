package com.chat.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.chat.constant.Constant;
import com.chat.service.ChatService;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;

@Component
@Sharable
public class WebSocketServerHandler extends SimpleChannelInboundHandler<WebSocketFrame>{

	@Autowired
	private ChatService chatService;
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame frame) throws Exception {
		handlerWebSocketFrame(ctx, frame);
	}

	private void handlerWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
		if (frame instanceof CloseWebSocketFrame) {
			String id = ctx.channel().id().asLongText();
			WebSocketServerHandshaker handshaker = Constant.webSocketServerHandshakerMap.get(id);
			if (handshaker == null) {
                sendErrorMessage(ctx, "不存在的客户端连接！");
            } else {
                handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
            }
            return;
		}
		
		String rquestContxt = ((TextWebSocketFrame)frame).text();
		JSONObject param = null;
		try {
			param = JSONObject.parseObject(rquestContxt);
		} catch (Exception e) {
			sendErrorMessage(ctx, "JSON字符串转换出错！");
			e.printStackTrace();
		}
		if (param == null) {
            sendErrorMessage(ctx, "参数为空！");
            return;
        }
		
		String type =(String) param.get("type");
		switch (type) {
		case "REGISTER":
            chatService.register(param, ctx);
            break;
		case "SINGLE_SENDING":
			chatService.singleSend(param, ctx);
			break;
		}
	}

	private void sendErrorMessage(ChannelHandlerContext ctx, String errorMsg) {
		ctx.channel().writeAndFlush(new TextWebSocketFrame(errorMsg));
	}

}
