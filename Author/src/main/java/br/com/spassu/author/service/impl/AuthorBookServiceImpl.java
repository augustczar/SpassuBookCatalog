package br.com.spassu.author.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.spassu.author.repository.AuthorBookRepository;
import br.com.spassu.author.service.AuthorBookService;

public class AuthorBookServiceImpl implements AuthorBookService {

	@Autowired
	AuthorBookRepository authorBookRepository;
}
