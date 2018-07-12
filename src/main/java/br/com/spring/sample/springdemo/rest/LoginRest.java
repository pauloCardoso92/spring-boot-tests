package br.com.spring.sample.springdemo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.spring.sample.springdemo.exception.AcessoNegadoException;
import br.com.spring.sample.springdemo.model.User;
import br.com.spring.sample.springdemo.service.TokenAuthenticationService;

@RestController
@RequestMapping("v1/login")
public class LoginRest {

	@Autowired
	private TokenAuthenticationService tokenService;

	@RequestMapping(method = RequestMethod.POST)
	public String realizarLogin(@RequestBody User user) throws AcessoNegadoException {
		tokenService.validarCrendenciais(user);
		return tokenService.gerarToken(user.getLogin());
	}
}
