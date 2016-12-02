package com.banshenghuo.nettyIM.server;

import java.util.ResourceBundle;

import com.banshenghuo.nettyIM.server.handler.EchoServerHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoServer {
	public static final ResourceBundle BUNDLE = ResourceBundle.getBundle("conf/netty");
	
	public void start() throws InterruptedException {
		// 创建EventLoopGroup
		NioEventLoopGroup group = new NioEventLoopGroup();
		
		try {
			Integer port = Integer.valueOf(BUNDLE.getString("server.port"));
			// 创建ServerBootstrap
			ServerBootstrap bootstrap = new ServerBootstrap();
			
			bootstrap.group(group)
			.channel(NioServerSocketChannel.class) // 使用指定NIO的传输Channel
			.localAddress(port)// 设置socket的地址端口
			.childHandler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					// 添加EchoServerHandler到Channle的ChannlePipeline
					ch.pipeline().addLast(new EchoServerHandler());
				}
			});
			
			// 绑定服务器;sync等待服务器关闭
			ChannelFuture channelFuture = bootstrap.bind().sync();
			System.out.println(EchoServer.class.getName() + " started and listen on " + channelFuture.channel().localAddress());
			// 关闭Channel和块，直到它被关闭
			channelFuture.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭EnventLoopGroup，释放所有资源
			group.shutdownGracefully().sync();
		}
	}
	
	public static void main(String[] args) {
		try {
			new EchoServer().start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
