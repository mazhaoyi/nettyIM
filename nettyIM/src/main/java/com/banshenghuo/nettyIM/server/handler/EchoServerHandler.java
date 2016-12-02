package com.banshenghuo.nettyIM.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.channel.ChannelHandler.Sharable;

//shareable 标识这个类的实例之间可以在channel里面共享
@Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf in = (ByteBuf) msg;
		// 日志消息输出到控制台
		System.out.println(in.toString(CharsetUtil.UTF_8));
		// 将接收的消息返回给发送者。注意，这里还没有冲刷数据
		ctx.write(Unpooled.copiedBuffer("hello world !", CharsetUtil.UTF_8));
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)// 冲刷所有待审消息到远程节点。关闭通道后，操作完成
		.addListener(ChannelFutureListener.CLOSE);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// 打印异常堆栈跟踪
		cause.printStackTrace();
		// 关闭通道
		ctx.close();
	}
	
}
