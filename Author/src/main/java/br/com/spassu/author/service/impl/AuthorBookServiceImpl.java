package br.com.spassu.author.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spassu.author.model.AuthorModel;
import br.com.spassu.author.repository.AuthorBookRepository;
import br.com.spassu.author.service.AuthorBookService;

@Service
public class AuthorBookServiceImpl implements AuthorBookService {

	@Autowired
	AuthorBookRepository authorBookRepository;
	
	  @Override
	    public boolean existsByAuthorAndBookId(AuthorModel authorModel, UUID bookId) {
	        return authorBookRepository.existsByAuthorAndBookId(authorModel, bookId);
	    }
}
