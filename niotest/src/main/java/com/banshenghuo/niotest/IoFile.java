package com.banshenghuo.niotest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class IoFile {
	public static void mvFile(String fromPath, String toPath) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			// create inputstream on originFile
			fis = new FileInputStream(fromPath);
			// create outputstream on destFile
			fos = new FileOutputStream(toPath);
			// create byte array buffer
			byte[] buf = new byte[1024];
			int read = -1;
			// inputstream read byte datas from originFile into byte array buffer
			while ((read = fis.read(buf)) != -1) {
				// outputstream write byte datas out to the destfile from byte array buffer
				fos.write(buf, 0, read);
			}
			fos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
