package ru.croc.task18.exceptions;

public class ShopException extends Exception {
	private ShopException(String message) {
		super(message);
	}

	public static ShopException ProductAlreadyExists(String vendorCode) {
		return new ShopException("Product with code " + vendorCode + " already exists");
	}

	public static ShopException ProductNotFound(String vendorCode) {
		return new ShopException("Product with code " + vendorCode + " was not found");
	}
}
