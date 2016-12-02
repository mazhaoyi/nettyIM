package com.banshenghuo.niotest.nio;

import org.junit.Test;

import com.banshenghuo.niotest.NioFile;

public class NioFileTest {
	@Test
	public void test0() {
		String fromPath = "G:\\eclipse-jee-neon-R-win32-x86_64.zip";
		String toPath = "G:\\ec.rar";
		NioFile.mvFile(fromPath, toPath);
	}
	@Test
	public void test() {
		String fromPath = "G:\\111.rar";
		String toPath = "G:\\111111.rar";
		NioFile.mvFile(fromPath, toPath);
	}
	@Test
	public void test2() {
		String fromPath = "G:\\222.txt";
		String toPath = "G:\\222222.txt";
		NioFile.mvFile(fromPath, toPath);
	}
	@Test
	public void test3() {
		String fromPath = "G:\\333.zip";
		String toPath = "G:\\333333.zip";
		NioFile.mvFile(fromPath, toPath);
	}
}
