package mqtt.msg;

import java.io.Serializable;
import java.util.Date;

public class MsgVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7345866071462193350L;
	private Integer id;
	private String value;
	private Date createTime;
	private Integer fromUserId;
	private Integer toUserId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getFromUserId() {
		return fromUserId;
	}
	public void setFromUserId(Integer fromUserId) {
		this.fromUserId = fromUserId;
	}
	public Integer getToUserId() {
		return toUserId;
	}
	public void setToUserId(Integer toUserId) {
		this.toUserId = toUserId;
	}
}
