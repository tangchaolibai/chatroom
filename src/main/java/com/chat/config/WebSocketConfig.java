package com.chat.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.chat.websocket.WebSocketChildChannelHandler;
import com.chat.websocket.WebSocketServer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

@Configuration
public class WebSocketConfig {
	@Resource(name = "webSocketChildChannelHandler")
	public WebSocketChildChannelHandler webSocketChildChannelHandler;

	@Bean
	public WebSocketServer getWebSocketServer() {
		WebSocketServer webSocketServer = new WebSocketServer();
		webSocketServer.setPort(1234);
		webSocketServer.setChildChannelHandler(webSocketChildChannelHandler);
		return webSocketServer;
	}

	@Bean("bossGroup")
	public EventLoopGroup getBossGroup() {
		return new NioEventLoopGroup();
	}

	@Bean("workerGroup")
	public EventLoopGroup getWorkerGroup() {
		return new NioEventLoopGroup();
	}

	@Bean
	public ServerBootstrap getServerBootstrap() {
		return new ServerBootstrap();
	}
}
