package br.com.spring.sample.springdemo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.spring.sample.springdemo.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

}
