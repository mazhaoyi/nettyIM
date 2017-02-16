package mqtt.server;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttProducer {
	/**
	 * 客户端
	 */
	private MqttClient client;
	/**
	 * TOPIC
	 */
	private MqttTopic mqttTopic;

	/**
	 * 生产者初始化创建客户端
	 * @param host
	 * @param clientId
	 * @throws MqttException
	 */
	public MqttProducer(String host, String clientId) throws MqttException {
		// MemoryPersistence设置clientid的保存形式，默认为以内存保存
		client = new MqttClient(host, clientId, new MemoryPersistence());
	}
	
	/**
	 * 客户端连接服务器，获取TOPIC
	 * @param topic
	 * @param userName
	 * @param password
	 */
	public void connect(String topic, String userName, String password) {
		MqttConnectOptions options = new MqttConnectOptions();
		options.setCleanSession(false);
		if (StringUtils.isNotBlank(userName)) {
			options.setUserName(userName);
		}
		if (StringUtils.isNotBlank(password)) {
			options.setPassword(password.toCharArray());
		}
		// 设置超时时间
		options.setConnectionTimeout(10);
		// 设置会话心跳时间
		options.setKeepAliveInterval(20);
		try {
			client.setCallback(new MqttProducerCallback());
			client.connect(options);
			MqttMessage message;
			message = new MqttMessage();
			message.setId(3);
//			message.setQos(1);
			message.setQos(2);
			message.setRetained(true);
//			message.setRetained(false);
			message.setPayload("天下无双".getBytes());
			client.publish(topic, message);
//			mqttTopic = client.getTopic(topic);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 断开连接
	 */
	public void disconnect() {
		try {
			client.disconnect();
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 发布消息
	 * @param message
	 * @throws MqttPersistenceException
	 * @throws MqttException
	 */
	public void publish(MqttMessage message) throws MqttPersistenceException, MqttException {
		MqttDeliveryToken token = mqttTopic.publish(message);
		token.waitForCompletion();
		System.out.println(token.isComplete() + "========");
	}

}