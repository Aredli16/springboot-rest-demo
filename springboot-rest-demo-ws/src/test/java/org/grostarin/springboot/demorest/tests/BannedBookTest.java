package org.grostarin.springboot.demorest.tests;

import org.grostarin.springboot.demorest.domain.BannedBook;
import org.grostarin.springboot.demorest.domain.Book;
import org.grostarin.springboot.demorest.repositories.BannedBookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BannedBookTest {
	@Autowired
	private BannedBookRepository bannedBookRepository;
	@Autowired
	private TestRestTemplate restTemplate;
	
	@BeforeEach
	void setUp() {
		bannedBookRepository.deleteAll();
	}
	
	@Test
	void shouldCreateBannedBook() {
		BannedBook BannedBook = new BannedBook("The Lord of the Rings", "J. R. R. Tolkien");
		
		ResponseEntity<BannedBook> response = restTemplate.postForEntity("/api/banned-books", BannedBook, BannedBook.class);
		
		assertEquals(200, response.getStatusCodeValue());
		assertNotNull(response.getBody());
		assertEquals("The Lord of the Rings", response.getBody().getTitle());
		assertEquals("J. R. R. Tolkien", response.getBody().getAuthor());
		
		Optional<BannedBook> savedBannedBook = bannedBookRepository.findById(response.getBody().getId());
		
		assertTrue(savedBannedBook.isPresent());
		assertEquals("The Lord of the Rings", savedBannedBook.get().getTitle());
		assertEquals("J. R. R. Tolkien", savedBannedBook.get().getAuthor());
	}
	
	@Test
	void shouldThrowExceptionIfCreateBannedBook() {
		BannedBook BannedBook = new BannedBook("The Lord of the Rings", "J. R. R. Tolkien");
		
		ResponseEntity<BannedBook> response = restTemplate.postForEntity("/api/banned-books", BannedBook, BannedBook.class);
		
		assertEquals(200, response.getStatusCodeValue());
		assertNotNull(response.getBody());
		
		Book book = new Book("The Lord of the Rings", "J. R. R. Tolkien");
		
		ResponseEntity<String> errorResponse = restTemplate.postForEntity("/api/books", book, String.class);
		
		assertEquals(400, errorResponse.getStatusCodeValue());
		assertNotNull(errorResponse.getBody());
		assertEquals("Book is banned", errorResponse.getBody());
	}
}
