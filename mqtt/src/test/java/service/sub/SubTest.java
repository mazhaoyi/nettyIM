package service.sub;

import mqtt.sevice.SubService;

public class SubTest {
	public static void main(String[] args) {
		String host = "tcp://10.0.2.139:1883";
		String clientId = "client23";
		String username = "sub_admin";
		String password = "admin123abc";
		
		String topicName = "message/update";
		// new 对象
		SubService subService = new SubService(host, clientId);
		// 连接服务器
		subService.connect(username, password, false);
		// 订阅TOPIC消息
		subService.subscribe(topicName, 2);
		
		// 取消订阅TOPIC消息
//		subService.unSubscribe(topicName);
//		subService.disconnect();
	}
}
