package br.com.spring.sample.springdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spring.sample.springdemo.model.Book;
import br.com.spring.sample.springdemo.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;
    
    public Book getBy(Integer id) {
        return repository.findById(id).get();
    }
    
    public Book save(Book book) {
        return repository.save(book);
    }
    
}
