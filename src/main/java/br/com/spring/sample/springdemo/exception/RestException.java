package br.com.spring.sample.springdemo.exception;

public class RestException extends Exception {

	private static final long serialVersionUID = 1L;
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public RestException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public RestException() {
		super();
	}

}
