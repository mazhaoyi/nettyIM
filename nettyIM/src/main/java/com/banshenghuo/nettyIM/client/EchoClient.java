package com.banshenghuo.nettyIM.client;

import com.banshenghuo.nettyIM.client.handler.EchoClientHandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class EchoClient {
	private final String host;
	private final int port;
	public EchoClient(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	public void start() throws InterruptedException {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			// 创建Bootstrap
			Bootstrap b = new Bootstrap();
			// 指定EventLoopGroup来处理客户端事件。由于我们用到了nio传输，所以用到了NioEventLoopGroup的实现
			b.group(group)
			// 指定Channel类型是NioSocketChannel
			.channel(NioSocketChannel.class)
			// 设置服务器的InetSocketAddress
			.remoteAddress(host, port)
			.handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					// 当建立一个新的通道的时候，创建添加EchoClientHandler实例，来处理事件
					ch.pipeline().addLast(new EchoClientHandler());
				}
			});
			// 连接到远程，等待连接完成
			ChannelFuture f = b.connect().sync();
			// 阻塞直到Channel关闭
			f.channel().closeFuture().sync();
		} finally {
			// 关闭线程池和释放所有资源
			group.shutdownGracefully().sync();
		}
	}
	
	public static void main(String[] args) {
		try {
			new EchoClient("127.0.0.1", 8088).start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
