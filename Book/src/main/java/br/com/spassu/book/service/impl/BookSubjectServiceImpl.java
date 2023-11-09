package br.com.spassu.book.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.spassu.book.repository.BookSubjectRepository;
import br.com.spassu.book.service.BookSubjectService;

public class BookSubjectServiceImpl implements BookSubjectService {

	@Autowired
	BookSubjectRepository bookSubjectRepository;
}
