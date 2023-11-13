package br.com.spassu.author.service;

import java.util.UUID;

import br.com.spassu.author.model.AuthorModel;

public interface AuthorBookService {

	boolean existsByAuthorAndBookId(AuthorModel authorModel, UUID bookId);

}
