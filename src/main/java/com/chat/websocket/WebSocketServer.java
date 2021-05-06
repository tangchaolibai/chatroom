package com.chat.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.FixedRecvByteBufAllocator;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;

public class WebSocketServer implements Runnable {

	@Autowired
	@Qualifier("bossGroup")
	private EventLoopGroup bossGroup;

	@Autowired
	@Qualifier("workerGroup")
	private EventLoopGroup workerGroup;
	@Autowired
	private ServerBootstrap serverBootstrap;

	private int port;
	private ChannelHandler childChannelHandler;
	private ChannelFuture serverChannelFuture;

	public WebSocketServer() {

	}

	@Override
	public void run() {
		build();
	}

	private void build() {
		try {
			serverBootstrap.group(bossGroup, workerGroup) // boss辅助客户端的tcp连接请求 worker负责与客户端之前的读写操作
					.channel(NioServerSocketChannel.class) // 配置客户端的channel类型
					.option(ChannelOption.SO_BACKLOG, 1024) // 配置TCP参数，握手字符串长度设置
					.option(ChannelOption.TCP_NODELAY, true) // TCP_NODELAY算法，尽可能发送大块数据，减少充斥的小块数据
					.childOption(ChannelOption.SO_KEEPALIVE, true)// 开启心跳包活机制，就是客户端、服务端建立连接处于ESTABLISHED状态，超过2小时没有交流，机制会被启动
					.childOption(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(592048))// 配置固定长度接收缓存区分配器
					.childHandler(childChannelHandler); // 绑定I/O事件的处理类,WebSocketChildChannelHandler中定义

			serverChannelFuture = serverBootstrap.bind(port).sync();
		} catch (Exception e) {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	public void close() {
		serverChannelFuture.channel().close();
		Future<?> bossGroupFuture = bossGroup.shutdownGracefully();
		Future<?> workerGroupFuture = workerGroup.shutdownGracefully();

		try {
			bossGroupFuture.await();
			workerGroupFuture.await();
		} catch (InterruptedException ignore) {
			ignore.printStackTrace();
		}
	}
	
	public ChannelHandler getChildChannelHandler() {
        return childChannelHandler;
    }

    public void setChildChannelHandler(ChannelHandler childChannelHandler) {
        this.childChannelHandler = childChannelHandler;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
