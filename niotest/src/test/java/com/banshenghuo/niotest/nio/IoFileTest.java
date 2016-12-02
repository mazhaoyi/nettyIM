package com.banshenghuo.niotest.nio;

import org.junit.Test;

import com.banshenghuo.niotest.IoFile;

public class IoFileTest {
	@Test
	public void test1() {
		String fromPath = "G:\\111.rar";
		String toPath = "G:\\111111.rar";
		IoFile.mvFile(fromPath, toPath);
	}
	@Test
	public void test2() {
		String fromPath = "G:\\222.txt";
		String toPath = "G:\\222222.txt";
		IoFile.mvFile(fromPath, toPath);
	}
	@Test
	public void test3() {
		String fromPath = "G:\\333.zip";
		String toPath = "G:\\333333.zip";
		IoFile.mvFile(fromPath, toPath);
	}
	
}
