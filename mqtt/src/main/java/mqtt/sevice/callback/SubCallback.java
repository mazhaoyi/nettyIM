package mqtt.sevice.callback;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * 接收订阅消息，回调类
 *
 */
public class SubCallback implements MqttCallback {
	
	@Override
	public void connectionLost(Throwable cause) {
		
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		// subscribe后得到的消息会执行到这里面
		System.out.println("接收消息主题:" + topic);
		System.out.println("接收消息ID:" + message.getId());
		System.out.println("接收消息Qos:" + message.getQos());
		System.out.println("接收消息内容:" + new String(message.getPayload()));
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		// publish后会执行到这里
		System.out.println("deliveryComplete---------" + token.isComplete());
	}

}
