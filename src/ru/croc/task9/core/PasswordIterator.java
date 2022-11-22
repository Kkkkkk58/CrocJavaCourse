package ru.croc.task9.core;

import java.util.Iterator;

public class PasswordIterator implements Iterator<String> {

	private final char[] currPassword;
	private final int passwordLength;
	private final int startPoint;
	private final String alphabet;

	public PasswordIterator(char[] currPassword, char[] alphabet, int startPoint) {
		this.passwordLength = currPassword.length;
		this.startPoint = startPoint;
		this.alphabet = new String(alphabet);
		this.currPassword = currPassword;
	}

	@Override
	public boolean hasNext() {
		for (int i = startPoint; i < passwordLength; ++i) {
			if (currPassword[i] != alphabet.charAt(alphabet.length() - 1)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public String next() {
		String password = new String(currPassword);
		if (hasNext()) {
			setNextLexicographicalPassword();
		}

		return password;
	}

	private void setNextLexicographicalPassword() {
		for (int i = passwordLength - 1; i >= startPoint; --i) {
			if (currPassword[i] != alphabet.charAt(alphabet.length() - 1)) {
				int alphabetIndex = alphabet.indexOf(currPassword[i]);
				currPassword[i] = alphabet.charAt(alphabetIndex + 1);
				break;
			}
			currPassword[i] = alphabet.charAt(0);
		}
	}
}
