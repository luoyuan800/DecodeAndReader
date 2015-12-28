/*
 * ReadUtils.java
 * Date: 12/23/2015
 * Time: 11:55 AM
 * 
 * Copyright 2015 luoyuan.
 * ALL RIGHTS RESERVED.
*/

package cn.gavin;

import java.io.*;
import java.util.Properties;

public class ReadUtils {
Properties props;
InputStream in;
OutputStream os;
boolean modify;

public ReadUtils() {
	props = new Properties();
	try {
		in = new BufferedInputStream(new FileInputStream(PathUtils.getJarRoot() + "/config.properties"));
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	try {
		if (in != null) {
			props.load(in);
		}
	} catch (IOException e) {
		e.printStackTrace();
	}
	modify = false;
}

public void save() throws IOException {
	if (modify) {
		OutputStream os = new FileOutputStream(new File(PathUtils.getJarRoot() + "/config.properties"));
		props.store(os, "");
		os.flush();
		os.close();
		modify = false;
	}
}

public void reOpen(){
	props = new Properties();
	try {
		if(in!=null){
			in.close();
		}
		in = new BufferedInputStream(new FileInputStream(PathUtils.getJarRoot() + "/config.properties"));
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	try {
		if (in != null) {
			props.load(in);
		}
	} catch (IOException e) {
		e.printStackTrace();
	}
	modify = false;
}

public void close() {

	try {
		save();
		in.close();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}

}

public String read(String name) {
	return props.getProperty(name);
}

public void write(String name, String value) {
	props.setProperty(name, value);
	modify = true;
}
}
