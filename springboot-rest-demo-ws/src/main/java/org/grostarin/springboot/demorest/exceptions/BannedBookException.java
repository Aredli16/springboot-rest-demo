package org.grostarin.springboot.demorest.exceptions;

public class BannedBookException extends RuntimeException {
	public BannedBookException(String message) {
		super(message);
	}
}
