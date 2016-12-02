package com.banshenghuo.niotest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;
import java.util.Set;

/**
 * 如果服务器关闭了链接，那么在客户端会报错，主机强行关闭了链接
 *
 */
public class NioSelectorServer {
	private Charset charset = Charset.forName("utf-8");
	private CharsetEncoder charsetEncoder = charset.newEncoder();
	private CharsetDecoder charsetDecoder = charset.newDecoder();
	
	public void server() {
		Selector selector = null;
		ServerSocketChannel ssChannel1 = null;
		try {
			ByteBuffer byteBuffer = ByteBuffer.allocate(48);
			// 创建selector
			selector = Selector.open();
			// 创建serverSocketChannel
			ssChannel1 = ServerSocketChannel.open();
			// 通道设置为非阻塞
			ssChannel1.configureBlocking(false);
			// 服务通道的服务套接字，用来接收连接
			ServerSocket ss = ssChannel1.socket();
			InetSocketAddress ia = new InetSocketAddress(8080);
			// 套接字绑定监听8080端口
			ss.bind(ia);
			
			// SelectionKey.OP_ACCEPT 监听accept 新建连接
			ssChannel1.register(selector, SelectionKey.OP_ACCEPT);
			// 该方法会阻塞，直到至少有一个已注册的事件发生，当有一个或者多个事件发生时候，该方法返回所发生的事件的数量
			while (true) {
				selector.select();
				// 返回所发生的事件的SelectionKey集合
				Set<SelectionKey> keys = selector.selectedKeys();
				Iterator<SelectionKey> ikeys = keys.iterator();
				byteBuffer.clear();
				while (ikeys.hasNext()) {
					SocketChannel sc = null;
					try {
						SelectionKey sk = ikeys.next();
						ikeys.remove();
						// 对于每一个SelectionKey，您必须确认发生的是什么I/O事件，以及这些事件影响哪些I/O对象
						// 可以肯定的说，readyOps告诉我们这个连接是新连接
						if (sk.isAcceptable()) {
							ServerSocketChannel ssc = (ServerSocketChannel) sk.channel();
							// 由于我们已经确定服务套接字上有一个传入连接在等待，所以我们不用担心ssc.accept会阻塞
							// 从服务套接字通道，接收新连接的套接字通道
							sc = ssc.accept();
							// 设置新连接套接字通道非阻塞
							sc.configureBlocking(false);
							// 由于接收这个连接目的是为了获取套接字的数据，所以必须把socketChannel注册到Selector上
							// 将SocketChannel注册用于读取OP_READ而不是接受新连接
							sc.register(selector, SelectionKey.OP_READ);
						} else if (sk.isReadable()) {
							// read the data
							sc = (SocketChannel) sk.channel();
							sc.read(byteBuffer);
							System.out.println("ttt");
							byteBuffer.flip();
							String msg = charsetDecoder.decode(byteBuffer).toString();
							String answer = null;
							switch (msg) {
							case "hi":
								answer = "hi u , morning !";
								break;
							case "hello":
								answer = "hello tom , have a good day !";
								break;
							case "how are you":
								answer = "oh fine, tku, and u ?";
								break;
							case "good":
								answer = "oh good?";
								break;
							case "day":
								answer = "have a good day";
								break;
							default:
								answer = "what are U 弄啥嘞 ?";
								break;
							}
							System.out.println(answer);
							sc.write(charsetEncoder.encode(CharBuffer.wrap(answer)));
						}
					} catch (Exception e) {
						e.printStackTrace();
						sc.close();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		NioSelectorServer ns = new NioSelectorServer();
		ns.server();
	}
}
