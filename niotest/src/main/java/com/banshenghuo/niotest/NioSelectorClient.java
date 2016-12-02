package com.banshenghuo.niotest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 如果客户端关闭了链接，那么服务器会报错，主机强行关闭了链接
 *
 */
public class NioSelectorClient {
	private Charset charset = Charset.forName("utf-8");
	private CharsetEncoder charsetEncoder = charset.newEncoder();
	private CharsetDecoder charsetDecoder = charset.newDecoder();
	
	private static ArrayBlockingQueue<String> words = null;
	
	public static void main(String[] args) {
		NioSelectorClient nsc = new NioSelectorClient();
		init();
		nsc.run();
	}
	
	private static void init() {
		words = new ArrayBlockingQueue<String>(5);
		try {
			words.put("hi");
			words.put("hello");
			words.put("how are you");
			words.put("good");
			words.put("day");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		SocketChannel sc = null;
		Selector selector = null;
		try {
			sc = SocketChannel.open();
			sc.configureBlocking(false);
			sc.connect(new InetSocketAddress(8080));
			selector = Selector.open();
			sc.register(selector, SelectionKey.OP_CONNECT);
			boolean isOver = false;
			while (!isOver) {
				selector.select();
				Iterator<SelectionKey> ite = selector.selectedKeys().iterator();
				while (ite.hasNext()) {
					SelectionKey sk = ite.next();
					ite.remove();
					if (sk.isConnectable()) {
						if (sc.isConnectionPending()) {
							if (sc.finishConnect()) {
								// 只有连接成功后才能注册OP_READ事件
								sk.interestOps(SelectionKey.OP_READ);
								sc.write(charsetEncoder.encode(CharBuffer.wrap(words.poll())));
								TimeUnit.SECONDS.sleep(1);
							} else {
								sk.cancel();
							}
						}
					} else if (sk.isReadable()) {
						ByteBuffer bb = ByteBuffer.allocate(128);
						sc.read(bb);
						bb.flip();
						CharBuffer cb = charsetDecoder.decode(bb);
						String answer = cb.toString();
						System.out.println(answer);
						String word = words.poll();
						if (word != null && !word.equals("")) {
							sc.write(charsetEncoder.encode(CharBuffer.wrap(word)));
						} else {
							isOver = true;
						}
						TimeUnit.SECONDS.sleep(2);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sc != null) {
				try {
					sc.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (selector != null) {
				try {
					selector.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
