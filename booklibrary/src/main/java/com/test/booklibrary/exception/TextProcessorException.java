package com.test.booklibrary.exception;

public class TextProcessorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TextProcessorException() {
		super();
	}

	public TextProcessorException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TextProcessorException(String message, Throwable cause) {
		super(message, cause);
	}

	public TextProcessorException(String message) {
		super(message);
	}

	public TextProcessorException(Throwable cause) {
		super(cause);
	}

}
