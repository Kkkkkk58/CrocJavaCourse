package ru.croc.task9.core.abstractions;

public interface PasswordCracker {

	String getPassword(int passwordLength, char[] alphabet, String hash);
}
