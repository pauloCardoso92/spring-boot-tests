package br.com.spring.sample.springdemo.service;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.spring.sample.springdemo.exception.AcessoNegadoException;
import br.com.spring.sample.springdemo.model.Credencial;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenAuthenticationService {

	private static final String KEY_SECRET = "IShPd";
	private static final String HEADER_STRING = "Authorization";

	public void validarCrendenciais(Credencial crendencial) throws AcessoNegadoException {
		if (!crendencial.getLogin().equals("teste") || !crendencial.getPassword().equals("123")) {
			throw new AcessoNegadoException(crendencial.getLogin());
		}
	}

	public String gerarToken(String login) {
		SignatureAlgorithm algoritimoAssinatura = SignatureAlgorithm.HS512;
		Calendar expira = Calendar.getInstance();
		expira.add(Calendar.MINUTE, 45);

		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(KEY_SECRET);
		SecretKeySpec key = new SecretKeySpec(apiKeySecretBytes, algoritimoAssinatura.getJcaName());

		JwtBuilder construtor = Jwts.builder() 
				.setIssuedAt(new Date())
				.setIssuer(login)
				.claim("id", 155)
				.signWith(algoritimoAssinatura, key)
				.setExpiration(expira.getTime());

		return construtor.compact();
	}
	
	public Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(KEY_SECRET))
				.parseClaimsJws(token).getBody();

		return new UsernamePasswordAuthenticationToken(claims, null, Collections.emptyList());
	}

}
