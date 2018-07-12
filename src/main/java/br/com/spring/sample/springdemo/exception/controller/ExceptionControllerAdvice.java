package br.com.spring.sample.springdemo.exception.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.spring.sample.springdemo.exception.AcessoNegadoException;
import br.com.spring.sample.springdemo.exception.DadosInvalidosException;
import br.com.spring.sample.springdemo.exception.response.ResponseError;
import io.jsonwebtoken.ClaimJwtException;

@ControllerAdvice
public class ExceptionControllerAdvice {

	private Logger logger = Logger.getLogger(ExceptionControllerAdvice.class.getName());

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseError> exceptionHandler(Exception ex) {
		return getInternalServerError(ex, "Ops! Ocorreu um erro inesperado, por favor contate o administrador");
	}

	@ExceptionHandler(AcessoNegadoException.class)
	public ResponseEntity<ResponseError> acessoNegadoExceptionHandler(AcessoNegadoException ex) {
		String msg = "Acesso negado ao usuario: " + ex.getLogin();
		ResponseError error = new ResponseError(HttpStatus.UNAUTHORIZED.value(), msg);

		logger.log(Level.SEVERE, msg, ex);
		return new ResponseEntity<ResponseError>(error, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(DadosInvalidosException.class)
	public ResponseEntity<ResponseError> dadosInvalidosExceptionHandler(DadosInvalidosException ex) {
		return getInternalServerError(ex, "Dados inválidos!");
	}

	@ExceptionHandler(ClaimJwtException.class)
	public ResponseEntity<ResponseError> tokenExpiradoExceptionHandler(ClaimJwtException ex) {
		return getInternalServerError(ex, "Token expirado: " + ex.getMessage());
	}

	private ResponseEntity<ResponseError> getInternalServerError(Exception ex, String msg) {
		ResponseError error = new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
		logger.log(Level.SEVERE, msg, ex);
		return new ResponseEntity<ResponseError>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}