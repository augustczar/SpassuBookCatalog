package br.com.spassu.author.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.spassu.author.model.AuthorModel;

public interface AuthorRepository extends JpaRepository<AuthorModel, UUID> {

	Optional<AuthorModel> findByAuthor(String name);

}
