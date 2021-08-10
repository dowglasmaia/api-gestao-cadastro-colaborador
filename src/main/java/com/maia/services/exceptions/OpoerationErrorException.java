package com.maia.services.exceptions;

public class OpoerationErrorException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public OpoerationErrorException() {
		super();

	}

	public OpoerationErrorException(String msg) {
		super(msg);
	}

}
