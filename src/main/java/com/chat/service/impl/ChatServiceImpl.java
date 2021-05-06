package com.chat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.chat.constant.ChatType;
import com.chat.constant.Constant;
import com.chat.entity.ResponseData;
import com.chat.service.ChatService;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

@Service
public class ChatServiceImpl implements ChatService {

	@Autowired 
	private StringRedisTemplate stringRedisTemplate;
	
	@Override
	public void register(JSONObject param, ChannelHandlerContext ctx) {
		String userId = (String)param.get("userId");
		Constant.onlineUserMap.put(userId, ctx);
		String responseDate = new ResponseData().success().setData("type", ChatType.REGISTER).toString();
		sendMessage(ctx, responseDate);
	}
	
	@Override
	public void singleSend(JSONObject param, ChannelHandlerContext ctx) {
		String fromUserId = (String)param.get("fromUserId");
        String toUserId = (String)param.get("toUserId");
        String content = (String)param.get("content");
     //   ChannelHandlerContext toUserCtx = Constant.onlineUserMap.get(toUserId);
        String responseData = new ResponseData().success()
        	.setData("fromUserId", fromUserId)
        	.setData("content", content)
        	.setData("toUserId", toUserId)
            .setData("type", ChatType.SINGLE_SENDING)
            .toString();
        stringRedisTemplate.convertAndSend("chatroom", responseData);
        //sendMessage(toUserCtx, responseData);
	}
	
	
	
	private void sendMessage(ChannelHandlerContext ctx, String message) {
		ctx.channel().writeAndFlush(new TextWebSocketFrame(message));
	}

}
