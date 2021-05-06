package com.chat.service;

import com.alibaba.fastjson.JSONObject;

import io.netty.channel.ChannelHandlerContext;

public interface ChatService {
	void singleSend(JSONObject param, ChannelHandlerContext ctx);

	void register(JSONObject param, ChannelHandlerContext ctx);
}
