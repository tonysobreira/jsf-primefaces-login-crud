package com.loginproject.util;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {

	private static final String IV = "AAAAAAAAAAAAAAAA";
	private static final String secretKey = "ABCDEFGHIJKLMNOP";
	private static final String algorithm = "AES/CBC/PKCS5Padding";

	public static String encrypt(String input) throws Exception {
		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(), getIv());
		byte[] cipherText = cipher.doFinal(input.getBytes());
		return Base64.getEncoder().encodeToString(cipherText);
	}

	public static String decrypt(String cipherText) throws Exception {
		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.DECRYPT_MODE, getSecretKey(), getIv());
		byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
		return new String(plainText);
	}

	public static SecretKey getSecretKey() throws Exception {
		return new SecretKeySpec(AESUtil.secretKey.getBytes("UTF-8"), "AES");
	}

	public static IvParameterSpec getIv() throws Exception {
		return new IvParameterSpec(AESUtil.IV.getBytes("UTF-8"));
	}

}
