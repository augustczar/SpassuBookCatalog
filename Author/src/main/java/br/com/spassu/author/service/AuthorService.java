package br.com.spassu.author.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

import br.com.spassu.author.model.AuthorModel;

public interface AuthorService {

	AuthorModel save(AuthorModel authorModel) throws Exception;

	Optional<AuthorModel> findByName(String name);

	Optional<AuthorModel> findById(UUID authorId);

	void delete(AuthorModel authorModel);

	List<AuthorModel> findAll(Specification<AuthorModel> spec);

	List<AuthorModel> findAll();

}
