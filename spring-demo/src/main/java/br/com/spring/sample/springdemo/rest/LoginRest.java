package br.com.spring.sample.springdemo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.spring.sample.springdemo.model.Credencial;
import br.com.spring.sample.springdemo.service.TokenAuthenticationService;

@RestController
@RequestMapping("v1/login")
public class LoginRest {

	@Autowired
	private TokenAuthenticationService tokenService;

	@RequestMapping(method = RequestMethod.POST)
	public String fazerLogin(@RequestBody Credencial credencial) throws Exception {
		tokenService.validarCrendenciais(credencial);
		return tokenService.gerarToken(credencial.getLogin());
	}
}
