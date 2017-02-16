package mqtt;

import mqtt.client.MqttConsumer;

public class ClientTest2 {
	public static void main(String[] args) {
		String host = "tcp://10.0.2.139:1883";
		String clientId = "client2";
		String mqttUsername = "sub_admin";
		String mqttPasswrod = "admin123abc";
		
		String topicName = "message/show";
		
		MqttConsumer mc = new MqttConsumer(host, clientId, mqttUsername, mqttPasswrod);
		
		mc.getMessage(topicName, 1);
	}
}