package com.banshenghuo.cop;

import java.io.Serializable;

public class Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -609930611195338467L;
	private Integer id;
	private String name;
	private String say;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSay() {
		return say;
	}
	public void setSay(String say) {
		this.say = say;
	}
}
