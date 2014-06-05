package com.app.util;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.output.ByteArrayOutputStream;



public class SDUtil {
	
	private static void closeInputStream(InputStream inputStream){
		try{
			if (inputStream != null){
				inputStream.close();
			}
		}catch (IOException e) {
			inputStream = null;
			e.printStackTrace();
		}
	}
	
	private static void closeOutputStream(OutputStream outputStream){
		try{
			if (outputStream != null){
				outputStream.close();
			}
		}catch (IOException e) {
			outputStream = null;
			e.printStackTrace();
		}
	}
	
	public static byte[] readFileToByteArray(File file){
		byte [] bs = null;
		BufferedInputStream bis = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			 bis = new BufferedInputStream(new FileInputStream(file));

			 byte[] b = new byte[1024];
			 int bSize = 0;
			 while ((bSize = bis.read(b)) != -1){
				 baos.write(b, 0, bSize);
			 }			 
			 bs = baos.toByteArray();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeOutputStream(baos);
			closeInputStream(bis);
			
		}
		return bs;
	}

}
