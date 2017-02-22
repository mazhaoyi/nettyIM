package service.pub;

import java.nio.charset.Charset;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import mqtt.sevice.PubService;

public class PubTest {
	/*
	 * 正常消息
	 */
	public static void main(String[] args) {
		String host = "tcp://10.0.2.139:1883";
		String clientId = "server";
		
		String username = "pub_admin";
		String password = "admin123abc";
		
		String topic = "message/update";
		
		// new 对象
		PubService pubService = new PubService(host, clientId);
		// 连接服务器
		pubService.connect(username, password);
		
		// 组装message
		MqttMessage message = new MqttMessage();
		message.setId(666666);
		message.setPayload("努力有用的话，还要天才做什么！！！！！你的反抗，让我诗兴大发！！！！！".getBytes(Charset.forName("UTF-8")));
//		message.setPayload("让妲己看看你的❤！".getBytes(Charset.forName("UTF-8")));
		message.setQos(2);
		message.setRetained(true);
		
		// 发布消息到TOPIC
		pubService.publish(topic, message);
		// 断开连接
		pubService.disconnect();
	}
	
	/*
	 * will消息
	 */
//	public static void main(String[] args) {
//		String host = "tcp://10.0.2.139:1883";
//		String clientId = "server";
//		
//		String username = "pub_admin";
//		String password = "admin123abc";
//		
//		String topic = "message/update";
//		
//		PubService pubService = new PubService(host, clientId);
//		
//		MqttConnectOptions options = pubService.getWillOptions(
//				username,
//				password,
//				true,
//				false,
//				topic,
//				"努力有用的话，还要天才做什么！!~~~你的反抗，让我诗兴大发！!".getBytes(Charset.forName("UTF-8")),
//				1
//			);
//		pubService.connect(options);
//		
//		pubService.publishWill(options);
//		
//		pubService.disconnect();
//	}
}
