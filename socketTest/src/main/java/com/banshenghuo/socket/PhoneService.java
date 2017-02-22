package com.banshenghuo.socket;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class PhoneService {
	public static void main(String[] args) {
		ServerSocket ss = null;
		Socket s = null;
		PrintStream ps = null;
		try {
			InetAddress address = InetAddress.getLocalHost();
			System.out.println("本机的IP地址是：" + address.getHostAddress());
			// 创建一个ServerSocket，监听30000端口
			ss = new ServerSocket(30000, 3);
			
			// 每当接收客户端的socket的请求，服务器端也产生一个socket
			s = ss.accept();
			// 将socket对应的输出流包装成对应的printStream
			ps = new PrintStream(s.getOutputStream());
			for (int i = 0; i < 100000; i++) {
				ps.println("Hello , I am Socket Server ! --> " + i);
				ps.flush();
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (s != null) {
				try {
					s.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
