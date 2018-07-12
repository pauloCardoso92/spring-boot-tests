package br.com.spring.sample.springdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spring.sample.springdemo.model.User;
import br.com.spring.sample.springdemo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public User getBy(String login, String password) {
		return repository.findByLoginAndPassword(login, password);
	}

}
