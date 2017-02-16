package mqtt.sevice;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;

import mqtt.sevice.abs.MqttServiceAbs;
import mqtt.sevice.callback.SubCallback;

/**
 * 从TOPIC接收消息的服务
 *
 */
public class SubService extends MqttServiceAbs {

	public SubService(String host, String clientId) {
		super(host, clientId);
	}
	
	@Override
	public void connect(MqttConnectOptions options) {
		getClient().setCallback(new SubCallback());
		try {
			getClient().connect(options);
		} catch (MqttSecurityException e) {
			e.printStackTrace();
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 订阅TOPIC的消息
	 * @param topic
	 * @param qos
	 */
	public void subscribe(String topic, int qos) {
		int[] qoss = {qos};
		String[] topicFilters = {topic};
		try {
			getClient().subscribe(topicFilters, qoss);
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 取消订阅TOPIC
	 * @param topic
	 */
	public void unSubscribe(String topic) {
		String[] topicFilters = {topic};
		try {
			getClient().unsubscribe(topicFilters);
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
}
