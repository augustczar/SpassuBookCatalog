package br.com.spassu.author.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spassu.author.model.AuthorModel;
import br.com.spassu.author.repository.AuthorRepository;
import br.com.spassu.author.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	AuthorRepository authorRepository;
	
	@Override
	public AuthorModel save(AuthorModel authorModel) throws Exception {
		Optional<AuthorModel> existedAuthor  = authorRepository.findByAuthor(authorModel.getName()); 
		
		if (existedAuthor.isPresent()) {
			throw new Exception("Author já existe!", null);
		}
		
		return authorRepository.save(authorModel);
	}

	@Override
	public Optional<AuthorModel> findByAuthor(String name) {
		Optional<AuthorModel> existedAuthor = authorRepository.findByAuthor(name);
		return Optional.of(existedAuthor.orElse(null));
	}

	@Override
	public Optional<AuthorModel> findById(UUID authorId) {
		return authorRepository.findById(authorId);
	}

	@Override
	public void delete(AuthorModel authorModel) {
		authorRepository.delete(authorModel);
		
	}

	@Override
	public List<AuthorModel> findAll() {
		return authorRepository.findAll();
	}

}
