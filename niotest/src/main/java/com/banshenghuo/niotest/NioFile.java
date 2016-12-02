package com.banshenghuo.niotest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioFile {
	public static void mvFile(String fromPath, String toPath) {
		FileChannel fromFc = null;
		FileChannel toFc = null;
		try {
			@SuppressWarnings("resource")
			RandomAccessFile fromFile = new RandomAccessFile(fromPath, "rw");
			@SuppressWarnings("resource")
			RandomAccessFile toFile = new RandomAccessFile(toPath, "rw");
			
			// get originFile's channel
			fromFc = fromFile.getChannel();
			// get destFile's channel
			toFc = toFile.getChannel();
			
			// create bytebuffer
			ByteBuffer bb = ByteBuffer.allocate(2560);
			
			while (fromFc.read(bb) != -1) {
				bb.flip();
				while (bb.hasRemaining()) {
					toFc.write(bb);
				}
				bb.clear();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fromFc != null) {
				try {
					fromFc.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (toFc != null) {
				try {
					toFc.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
