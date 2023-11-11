package br.com.spassu.book.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.spassu.book.model.BookModel;

public interface BookRepository extends JpaRepository<BookModel, UUID>{
	
  	Optional<BookModel> findByTitle(String title);

	List<BookModel> findAll(Specification<BookModel> spec);

}
