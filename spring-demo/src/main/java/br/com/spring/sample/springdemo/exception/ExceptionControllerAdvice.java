package br.com.spring.sample.springdemo.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {
	
	private Logger logger = Logger.getLogger(ExceptionControllerAdvice.class.getName());

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		
		error.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage("Ops! Ocorreu um erro inesperado, por favor contate o administrador");
		
		logger.log(Level.SEVERE, "Ocorreu um erro inesperado", ex);
		
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(AcessoNegadoException.class)
	public ResponseEntity<ErrorResponse> acessoNegadoexceptionHandler(AcessoNegadoException ex) {
		ErrorResponse error = new ErrorResponse();
		String msg = "Acesso negado ao usuario: " + ex.getLogin();
		
		error.setCode(HttpStatus.UNAUTHORIZED.value());
		error.setMessage(msg);
		
		logger.log(Level.SEVERE, msg, ex);
		
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.UNAUTHORIZED);
	}
}