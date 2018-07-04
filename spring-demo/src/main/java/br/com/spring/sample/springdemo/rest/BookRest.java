package br.com.spring.sample.springdemo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.spring.sample.springdemo.exception.RestException;
import br.com.spring.sample.springdemo.model.Book;
import br.com.spring.sample.springdemo.service.BookService;

@RestController
@RequestMapping("v1/book")
public class BookRest {

	@Autowired
	private BookService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Book getBy(@PathVariable Integer id) throws RestException {
		return service.getBy(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Book> getAll() {
		return service.getAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Book save(@RequestBody Book book) {
		return service.save(book);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public void deleteById(@PathVariable Integer id) {
		service.deleteById(id);
	}

}
