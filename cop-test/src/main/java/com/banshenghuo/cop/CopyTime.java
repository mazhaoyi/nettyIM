package com.banshenghuo.cop;

import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;

import org.apache.commons.beanutils.BeanUtils;

import mb_redis_util.com.doordu.util.CloneUtils;

public class CopyTime {
	/**
	 * 经过测试for循环，json copy最稳定
	 * @param args
	 */
	public static void main(String[] args) {
		Person p = new Person();
		p.setId(1);
		p.setName("name" + 1);
		p.setSay("hello " + 1);
		
		Person p2 = new Person();
		Long start1 = Calendar.getInstance().getTimeInMillis();
		try {
			for (int i = 0; i < 1; i++) {
				BeanUtils.copyProperties(p2, p);
			}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Long start2 = Calendar.getInstance().getTimeInMillis();
		System.out.println(start2 - start1);
		
		
		p2 = new Person();
		Long s1 = Calendar.getInstance().getTimeInMillis();
		try {
			for (int i = 0; i < 1; i++) {
				p2 = CloneUtils.byteClone(p);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Long s2 = Calendar.getInstance().getTimeInMillis();
		System.out.println(s2 - s1);
		
		p2 = new Person();
		Long s11 = Calendar.getInstance().getTimeInMillis();
		try {
			for (int i = 0; i < 1; i++) {
				p2 = CloneUtils.jsonClone(p);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Long s22 = Calendar.getInstance().getTimeInMillis();
		System.out.println(s22 - s11);
	}
}
