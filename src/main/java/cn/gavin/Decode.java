/*
 * Decode.java
 * Date: 12/21/2015
 * Time: 5:01 PM
 * 
 * Copyright 2015 luoyuan.
 * ALL RIGHTS RESERVED.
*/

package cn.gavin;

import sun.security.rsa.RSAPrivateCrtKeyImpl;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.Arrays;

public class Decode {
private Key key;
private Cipher cipher;

public Decode(String privateKeyString) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
	ArrayList<Byte> bytes = new ArrayList<Byte>();
	for(String s : privateKeyString.split(",")){
		bytes.add(Byte.valueOf(s.trim()));
	}
	byte[] privateKey = new byte[bytes.size()];
	for(int i = 0; i< bytes.size(); i++){
		privateKey[i] = bytes.get(i);
	}
	key = RSAPrivateCrtKeyImpl.newKey(privateKey);
	cipher = Cipher.getInstance("RSA");
	cipher.init(Cipher.DECRYPT_MODE, key);
}

public String decode(
		String source) throws BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
	byte[] encode = cipher.doFinal(source.getBytes());
	if (encode != null) {
		return new String(encode, "utf-8");
	} else {
		return null;
	}
}public String decode(
		byte[] source) throws BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
	byte[] encode = cipher.doFinal(source);
	if (encode != null) {
		return new String(encode, "utf-8");
	} else {
		return null;
	}
}

public static String geneKeys() throws NoSuchAlgorithmException, UnsupportedEncodingException {
	KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");//初始化一个KeyPairGenerator
	keyGenerator.initialize(512);//设置密钥长度为1024
	KeyPair keyPair = keyGenerator.generateKeyPair();//RSA加密需要生成相对应的公钥和私钥
	RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();//获得私钥
	RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();//获得公钥
	ComputeKey.writeKey(Arrays.toString(privateKey.getEncoded()).replaceAll("\\[|\\]",""));
	String key = Arrays.toString(publicKey.getEncoded());
	key = key.replaceAll("\\[|\\]","");
	return key;
}

public static void main(String...args) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
	String source = "78, -82, 98, 19, -18, 65, 76, 12, 105, -58, 118, -35, 61, 48, 103, 127, -123, -112, -37, -16, " +
			"-101, 48, -4, -70, 51, -123, -24, 56, 0, 63, 26, -66, -82, 96, -91, 106, 37, 126, 83, 43, 115, 18, 67, 20," +
			" 122, -90, -86, -12, -83, -52, -52, -55, 7, -93, -120, 42, 26, 5, 97, -14, 61, -15, 34, -72";
	ArrayList<Byte> bytes = new ArrayList<Byte>();
	for(String s : source.split(",")){
		bytes.add(Byte.valueOf(s.trim()));
	}
	byte[] publicBytes = new byte[bytes.size()];
	for(int i = 0; i< bytes.size(); i++){
		publicBytes[i] = bytes.get(i);
	}
	Decode decode = new Decode("48, -126, 1, 85, 2, 1, 0, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 1, 5, 0, 4, " +
			"-126, 1, 63, 48, -126, 1, 59, 2, 1, 0, 2, 65, 0, -77, 43, 110, 73, 48, 23, -33, -5, 70, -4, 61, 52, 116, " +
			"10, 4, 6, -49, -5, 88, -106, 20, -2, 106, 40, 81, -19, -34, 120, -126, 85, -64, -103, 20, -15, 44, 20, " +
			"-79, 80, -42, -66, 68, 28, -48, 55, 2, -65, -28, -74, -49, 87, 119, 115, 103, -126, -52, 124, 44, 93, 127, " +
			"101, 95, 64, -42, -95, 2, 3, 1, 0, 1, 2, 65, 0, -80, 123, 117, 52, -82, 36, -74, 65, 98, -40, -114, -55, " +
			"30, 11, -57, -113, -106, 73, -12, 38, 81, 35, -111, 25, -118, 42, 13, -37, 18, 54, -71, 111, -22, 64, -81," +
			" 46, 29, 91, 57, 86, -59, 87, -67, 31, 85, 5, 69, -35, 53, -64, 104, 28, -55, -120, -64, -38, -65, 76, -73," +
			" -45, -122, -15, 75, 41, 2, 33, 0, -37, 111, 46, -44, 81, -122, 118, -23, 15, -56, -83, 97, 13, -84, 99, 0, " +
			"-53, 53, 38, -63, 84, -23, -128, -99, 103, -81, 59, -14, 10, -10, -45, 15, 2, 33, 0, -47, 6, -102, -124, -41," +
			" -27, -62, 46, 120, 15, 112, -36, 46, -48, 97, 85, 85, -89, 103, 89, -56, 70, -88, 124, -119, -72, -94, 26, 41," +
			" 122, -5, 79, 2, 32, 29, -84, 21, 43, 4, -77, 40, 21, 47, -37, 53, 6, -80, -80, -92, -38, -112, -97, -23, 83, " +
			"-81, 69, -69, -20, -116, -2, -73, 30, 109, -71, 30, -91, 2, 33, 0, -108, -110, -60, 12, 62, -59, -126, 51, -77," +
			" -123, 14, 44, -125, 93, -4, -127, 66, 99, -66, 51, 116, 6, -67, 69, -32, 66, -117, 126, 91, 19, -98, 67, 2," +
			" 32, 110, -122, -106, -108, 54, 100, 63, 50, 34, -111, -101, -37, -41, -88, 127, 126, 64, -116, 80, -25, 110, " +
			"27, -122, 72, -2, -94, 32, 58, -12, -32, -78, 110");
	System.out.println(decode.decode(publicBytes));
}
}
