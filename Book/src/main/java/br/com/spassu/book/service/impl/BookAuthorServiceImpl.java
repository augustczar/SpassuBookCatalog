package br.com.spassu.book.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spassu.book.repository.BookAuthorRepository;
import br.com.spassu.book.service.BookAuthorService;

@Service
public class BookAuthorServiceImpl implements BookAuthorService{

	@Autowired
	BookAuthorRepository bookAuthorRepository; 
}
