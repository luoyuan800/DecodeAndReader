/*
 * PathUtils.java
 * Date: 12/21/2015
 * Time: 5:56 PM
 * 
 * Copyright 2015 luoyuan.
 * ALL RIGHTS RESERVED.
*/

package cn.gavin;

import java.io.File;

public class PathUtils {

public static String getJarRoot(){
	String jarPath= PathUtils.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	int i = jarPath.length() - 1;
	if(jarPath.endsWith("/")){
		i -= 1;
	}
	int lastIndex = 0;
	for(; i>= 0; i--){
		if(jarPath.charAt(i) == '/'){
			lastIndex = i;
			break;
		}
	}
	return jarPath.substring(0,lastIndex);
}

public static File getResourcePath(String name){
	File file = new File(getJarRoot() + "/resource/" + name);
	boolean rs = true;
	if(!file.exists()|| !file.isDirectory()){
		rs = file.mkdirs();
	}
	if(rs){
		return new File(file.getPath() + "/" + name + ".r");
	}
	return null;
}

}
