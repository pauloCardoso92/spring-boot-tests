package br.com.spring.sample.springdemo.exception;

public class AcessoNegadoException extends Exception {

	private static final long serialVersionUID = 1L;
	private String login;

	public AcessoNegadoException() {
	}
	
	public AcessoNegadoException(String login) {
		this.login = login;
	}

	public String getLogin() {
		return this.login;
	}

}
