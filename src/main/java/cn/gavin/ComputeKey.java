/*
 * WriteComputeKey.java
 * Date: 12/22/2015
 * Time: 4:39 PM
 * 
 * Copyright 2015 luoyuan.
 * ALL RIGHTS RESERVED.
*/

package cn.gavin;

import java.io.*;

public class ComputeKey {
public static void writeKey(String key) {
	File file = new File("C:/Users/Public/baidu");
	boolean noError = true;
	if (!file.exists()) {
		noError = file.mkdirs();
	}
	File keyFile = new File(file.getPath() + "/.catch.jpg");
	if (keyFile.exists()) {
		keyFile.delete();
	}
	try {
		keyFile.createNewFile();
		FileWriter writer = new FileWriter(keyFile);
		writer.write(key);
		writer.flush();
		writer.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
}

public static String readKey() {
	File keyFile = new File("C:/Users/Public/baidu" + "/.catch.jpg");
	if (keyFile.exists()) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(keyFile));
			return reader.readLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	return "";
}

public static void deleteKey() {
	File keyFile = new File("C:/Program Files/baidu" + "/.catch.jpg");
	if (keyFile.exists()) {
		keyFile.delete();
	}
}
}
