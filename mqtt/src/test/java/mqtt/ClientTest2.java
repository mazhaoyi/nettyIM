package mqtt;

import mqtt.client.MqttConsumer;

public class ClientTest2 {
	public static void main(String[] args) {
		String host = "tcp://10.0.0.193:1883";
		String clientId = "client2";
		String mqttUsername = "admin";
		String mqttPasswrod = "admin";
		
		String topicName = "tokudu/yzq124";
		
		MqttConsumer mc = new MqttConsumer(host, clientId, mqttUsername, mqttPasswrod);
		
		mc.getMessage(topicName);
	}
}
