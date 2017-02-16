package mqtt.sevice.abs;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * MQTT发布消息到TOPIC主题
 */
public abstract class MqttServiceAbs {
	/**
	 * serverURL
	 */
	private String host;
	/**
	 * clientID
	 */
	private String clientId;
	/**
	 * MQTT客户端
	 */
	private MqttClient client;
	
	public MqttServiceAbs(String host, String clientId) {
		this.host = host;
		this.clientId = clientId;
		getClient();
	}
	/**
	 * 初始化MQTT客户端
	 * @return
	 */
	public MqttClient getClient() {
		try {
			if (this.client == null) {
				this.client = new MqttClient(host, clientId, new MemoryPersistence());
			}
		} catch (MqttException e) {
			e.printStackTrace();
		}
		return this.client;
	}
	
	/**
	 * MQTT客户端选项，不支持WILL
	 * @param username
	 * @param password
	 * @param cleanSession
	 * @return
	 */
	public MqttConnectOptions getOptions(String username, String password, Boolean cleanSession) {
		MqttConnectOptions options = new MqttConnectOptions();
		options.setCleanSession(cleanSession);
		options.setUserName(username);
		options.setPassword(password.toCharArray());
		// 超时时间
		options.setConnectionTimeout(10);
		// 会话心跳时间
		options.setKeepAliveInterval(20);
		return options;
	}
	
	/**
	 * MQTT WILL客户端选项，单支持WILL
	 * @param username
	 * @param password
	 * @param retained
	 * @param cleanSession
	 * @param topic
	 * @param payload
	 * @param qos
	 * @return
	 */
	public MqttConnectOptions getWillOptions(String username, String password, Boolean retained, Boolean cleanSession, String topic, byte[] payload, int qos) {
		MqttConnectOptions options = new MqttConnectOptions();
		options.setCleanSession(cleanSession);
		options.setUserName(username);
		options.setPassword(password.toCharArray());
		// 超时时间
		options.setConnectionTimeout(10);
		// 会话心跳时间
		options.setKeepAliveInterval(20);
		// will选项
		options.setWill(topic, payload, qos, retained);
		return options;
	}
	
	/**
	 * MQTT客户端连接服务器，不支持WILL
	 * @param username
	 * @param password
	 */
	public void connect(String username, String password) {
		connect(username, password, false);
	}
	
	/**
	 * MQTT客户端连接服务器，不支持WILL
	 * @param username
	 * @param password
	 * @param retained
	 * @param cleanSession
	 */
	public void connect(String username, String password, Boolean cleanSession) {
		MqttConnectOptions options = getOptions(username, password, cleanSession);
		connect(options);
	}
	
	/**
	 * MQTT客户端连接服务器
	 * @param options 客户端选项
	 */
	public abstract void connect(MqttConnectOptions options);
	
	/**
	 * 断开连接
	 */
	public void disconnect() {
		try {
			client.disconnect();
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
}
