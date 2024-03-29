package br.com.spassu.book.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.spassu.book.model.BookModel;
import br.com.spassu.book.repository.BookRepository;
import br.com.spassu.book.service.BookService;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	BookRepository bookRepository;

	@Override
	public BookModel save(BookModel bookModel) throws Exception {
		return bookRepository.save(bookModel);
	}

	@Override
	public Optional<BookModel> findByTitle(String title) {
		
		Optional<BookModel> existedBook = bookRepository.findByTitle(title);
		return existedBook;
	}

	@Override
	public Optional<BookModel> findById(UUID bookId) {
		return bookRepository.findById(bookId);
	}

	@Override
	public void delete(BookModel bookModel) {
		bookRepository.delete(bookModel);
		
	}

	@Override
	public List<BookModel> findAll() {
		return bookRepository.findAll();
	}

	@Override
	public List<BookModel> findAll(Specification<BookModel> spec) {
		return bookRepository.findAll(spec);
	}
}
