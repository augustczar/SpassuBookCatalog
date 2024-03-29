package br.com.spassu.book.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

import br.com.spassu.book.model.BookModel;

public interface BookService {

	BookModel save(BookModel bookModel) throws Exception;

	Optional<BookModel> findByTitle(String title);
	
	Optional<BookModel> findById(UUID bookId);

	void delete(BookModel bookModel);

	List<BookModel> findAll();

	List<BookModel> findAll(Specification<BookModel> spec);

}
