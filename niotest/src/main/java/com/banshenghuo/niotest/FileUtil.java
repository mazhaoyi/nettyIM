package com.banshenghuo.niotest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileUtil {
	public static void moveNio(String originSrc, String destSrc) {
		FileChannel originChannel = null;
		FileChannel destChannel = null;
		
		try {
			@SuppressWarnings("resource")
			RandomAccessFile originFile = new RandomAccessFile(originSrc, "rw");
			@SuppressWarnings("resource")
			RandomAccessFile destFile = new RandomAccessFile(destSrc, "rw");
			
			originChannel = originFile.getChannel();
			destChannel = destFile.getChannel();
			
			ByteBuffer bb = ByteBuffer.allocate(2560);
			
			while (originChannel.read(bb) != -1) {
				bb.flip();
				while (bb.hasRemaining()) {
					destChannel.write(bb);
				}
				bb.clear();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (originChannel != null) {
				try {
					originChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (destChannel != null) {
				try {
					destChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
