package br.com.spring.sample.springdemo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.spring.sample.springdemo.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	User findByLoginAndPassword(String login, String password);

}
