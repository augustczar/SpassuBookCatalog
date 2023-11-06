package br.com.spassu.book.service;

import java.util.Optional;
import java.util.UUID;

import br.com.spassu.book.model.BookModel;

public interface BookService {

	BookModel save(BookModel bookModel) throws Exception;

	Optional<BookModel> findByTitle(String title);
	
	Optional<BookModel> findById(UUID bookId);

}
