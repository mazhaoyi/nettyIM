package mqtt;

import java.util.Calendar;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.alibaba.fastjson.JSON;

import mqtt.msg.MsgVo;
import mqtt.server.MqttProducer;

public class ServerTest {
	public static void main(String[] args) throws MqttException {
		String host = "tcp://10.0.2.139:1883";
		String clientId = "server";
		String userName = "pub_admin";
		String password = "admin123abc";
		
		String topic = "message/show";
		MqttProducer server = new MqttProducer(host, clientId);
		server.connect(topic, userName, password);
		
		
//		MsgVo mv = new MsgVo();
//		mv.setCreateTime(Calendar.getInstance().getTime());
//		mv.setFromUserId(2000);
//		mv.setId(1);
//		mv.setToUserId(2001);
////		mv.setValue("努力有用的话，还要天才做什么~~~！！~~~~~~~");
//		mv.setValue("天下无双!!");
//		String msg = JSON.toJSONString(mv);
//		
////		MqttMessage message;
////		message = new MqttMessage();
////		message.setId(3);
//////		message.setQos(1);
////		message.setQos(2);
////		message.setRetained(true);
//////		message.setRetained(false);
////		message.setPayload(msg.getBytes());
////		server.publish(message);
////		System.out.println(message.isRetained() + "------ratained状态");
		server.disconnect();
	}
}
