package com.banshenghuo.niotest.hashtest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Mytest {
	public static class User{
		private Integer id;
		private String name;
		private Integer age;
		public User(Integer id, String name, Integer age) {
			this.id = id;
			this.name = name;
			this.age = age;
		}
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
		public Integer getAge() {
			return age;
		}
		public void setAge(Integer age) {
			this.age = age;
		}
		@Override
		public int hashCode() {
			final int prime = 30;
			int result = 1;
			result = prime * result + (id == null ? 0 : id);
			result = prime * result + (name == null ? 0 : name.hashCode());
			result = prime * result + (age == null ? 0 : age);
			return result;
		}
	}
	public static void main(String[] args) {
		User u1 = new User(1, "张三", 20);
		User u2 = new User(2, "李四", 21);
		Set<User> set = new HashSet<>();
		List<User> list = new ArrayList<>();
		
		set.add(u1);
		set.add(u2);
		
		list.add(u1);
		list.add(u2);
		
		u1.setId(3);
		set.remove(u1);
		list.remove(u1);
		
		System.out.println(set.size());
		System.out.println(list.size());
		
	}
}
