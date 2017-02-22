package com.banshenghuo.queue;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

public class QueueTest {
	@Test
	public void linkedList() {
		// add() 和 remove() 方法在失败的时候会抛异常
		Queue<String> queue = new LinkedList<>();
		queue.offer("b");
		queue.offer("c");
		queue.offer("d");
		queue.offer("a");
		queue.offer("A");
		
		for (String q : queue) {
			System.out.print(q + "\t");
		}
		System.out.println();
		
		System.out.println("=============================");
		System.out.println("poll ----> " + queue.poll());// 返回第一个元素，并在队列里面删除
		
		System.out.println("============queue.element()如果队列为空就会抛出错我，queue.peek()返回null=================");
		System.out.println("element ----> " + queue.peek());// 返回第一个元素，不清除元素
		
		for (String q : queue) {
			System.out.print(q + "\t");
		}
		System.out.println();
		
		queue.clear();
		System.out.println("============queue.element()如果队列为空就会抛出错我，queue.peek()返回null=================");
		System.out.println("element ----> " + queue.peek());// 返回第一个元素，不清除元素
//		System.out.println("element ----> " + queue.element());// 返回第一个元素，不清除元素
	}
}
