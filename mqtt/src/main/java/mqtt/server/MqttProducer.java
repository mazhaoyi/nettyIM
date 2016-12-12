package mqtt.server;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttProducer {

	public static final String HOST = "tcp://10.0.0.193:1883";

	public static final String TOPIC = "tokudu/yzq124";
//	public static final String TOPIC = "tokudu/aaa";
	
	private static final String clientid = "server";

	private MqttClient client;
	private MqttTopic topic;
	private String userName = "test";
	private String passWord = "test";

	public MqttProducer() throws MqttException {
		// MemoryPersistence设置clientid的保存形式，默认为以内存保存
		client = new MqttClient(HOST, clientid, new MemoryPersistence());
		connect();
	}

	private void connect() {
		MqttConnectOptions options = new MqttConnectOptions();
		options.setCleanSession(false);
		options.setUserName(userName);
		options.setPassword(passWord.toCharArray());
		// 设置超时时间
		options.setConnectionTimeout(10);
		// 设置会话心跳时间
		options.setKeepAliveInterval(20);
		try {
			client.setCallback(new MqttProducerCallback());
			client.connect(options);
			topic = client.getTopic(TOPIC);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void disconnect() {
		try {
			client.disconnect();
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void publish(MqttMessage message) throws MqttPersistenceException, MqttException {
		MqttDeliveryToken token = topic.publish(message);
		token.waitForCompletion();
		System.out.println(token.isComplete() + "========");
	}

}