package br.com.spassu.book.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spassu.book.repository.BookSubjectRepository;
import br.com.spassu.book.service.BookSubjectService;

@Service
public class BookSubjectServiceImpl implements BookSubjectService {

	@Autowired
	BookSubjectRepository bookSubjectRepository;
}
