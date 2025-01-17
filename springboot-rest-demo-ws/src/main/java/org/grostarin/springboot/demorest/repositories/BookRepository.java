package org.grostarin.springboot.demorest.repositories;

import org.grostarin.springboot.demorest.domain.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
	List<Book> findByTitle(String title);
}
