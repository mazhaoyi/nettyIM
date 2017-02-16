package mqtt.sevice;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;

import mqtt.sevice.abs.MqttServiceAbs;
import mqtt.sevice.callback.PubCallback;

/**
 * 发布消息到TOPIC的服务
 *
 */
public class PubService extends MqttServiceAbs {

	public PubService(String host, String clientId) {
		super(host, clientId);
	}

	@Override
	public void connect(MqttConnectOptions options) {
		getClient().setCallback(new PubCallback());
		try {
			getClient().connect(options);
		} catch (MqttSecurityException e) {
			e.printStackTrace();
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 发布消息到TOPIC
	 * @param topic
	 * @param message
	 */
	public void publish(String topic, MqttMessage message) {
		try {
			getClient().publish(topic, message);
		} catch (MqttPersistenceException e) {
			e.printStackTrace();
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 发布will消息到TOPIC
	 * @param options 客户端选项，需要WILL选项支持
	 */
	public void publishWill(MqttConnectOptions options) {
		String topic = options.getWillDestination();
		MqttMessage message = options.getWillMessage();
		try {
			getClient().publish(topic, message);
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

}
