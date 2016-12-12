package mqtt;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import mqtt.server.MqttProducer;

public class ServerTest {
	public static void main(String[] args) throws MqttException {
		MqttProducer server = new MqttProducer();
		MqttMessage message;
		message = new MqttMessage();
		message.setQos(1);
		message.setRetained(true);
		message.setPayload("good morning 11122".getBytes());
		server.publish(message);
		System.out.println(message.isRetained() + "------ratained状态");
		server.disconnect();
	}
}
