package org.grostarin.springboot.demorest.controllers;

import org.grostarin.springboot.demorest.domain.BannedBook;
import org.grostarin.springboot.demorest.services.BannedBookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/banned-books")
public class BannedBookController {
	@Autowired
	private BannedBookServices bannedBookServices;
	
	@PostMapping
	public BannedBook addBannedBook(@RequestBody BannedBook book) {
		return bannedBookServices.addBannedBook(book);
	}
}
