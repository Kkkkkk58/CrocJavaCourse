package ru.croc.task9.core;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import ru.croc.task9.core.abstractions.PasswordHasher;

public class MD5PasswordHasher implements PasswordHasher {

	private static final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();

	private static String toHexString(byte[] bytes) {
		StringBuilder hex = new StringBuilder(bytes.length * 2);
		for (byte b : bytes) {
			hex.append(HEX_DIGITS[(b & 0xff) >> 4]);
			hex.append(HEX_DIGITS[b & 0x0f]);
		}
		return hex.toString();
	}

	public String hashPassword(String password) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		digest.update(password.getBytes());
		byte[] bytes = digest.digest();
		return toHexString(bytes);
	}
}
