package org.grostarin.springboot.demorest.repositories;

import org.grostarin.springboot.demorest.domain.BannedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannedBookRepository extends JpaRepository<BannedBook, Long> {
	boolean existsBannedBookByTitleAndAuthor(String title, String author);
}
