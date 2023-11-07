package br.com.spassu.author.service;

import java.util.Optional;
import java.util.UUID;

import br.com.spassu.author.model.AuthorModel;

public interface AuthorService {

	AuthorModel save(AuthorModel authorModel) throws Exception;

	Optional<AuthorModel> findByAuthor(String name);

	Optional<AuthorModel> findById(UUID authorId);

	void delete(AuthorModel authorModel);

}
