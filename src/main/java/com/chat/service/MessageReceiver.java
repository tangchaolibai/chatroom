package com.chat.service;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.chat.constant.Constant;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

@Component
public class MessageReceiver {

    /**接收消息的方法*/
    public void receiveMessage(String message){
    	JSONObject json = JSONObject.parseObject(message);
    	JSONObject data = (JSONObject)json.get("data");
    	String toUserId = (String)data.get("toUserId");
    	ChannelHandlerContext toUserCtx = Constant.onlineUserMap.get(toUserId);
    	if (toUserCtx != null) {
    		sendMessage(toUserCtx, message);
		}
    }

    private void sendMessage(ChannelHandlerContext ctx, String message) {
		ctx.channel().writeAndFlush(new TextWebSocketFrame(message));
	}
    
}