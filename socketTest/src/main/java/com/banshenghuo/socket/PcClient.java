package com.banshenghuo.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

import javax.net.SocketFactory;

import org.apache.commons.lang.StringUtils;

public class PcClient {
	public static void main(String[] args) {
		Socket socket = null;
		BufferedReader br = null;
		try {
			socket = SocketFactory.getDefault().createSocket("10.0.2.131", 30000);
			// 将socket对应的输入流包装成bufferedReader
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(), Charset.forName("utf-8")));
			String line = null;
			while (StringUtils.isNotBlank(line = br.readLine())) {
				System.out.println("服务器说：" + line);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
