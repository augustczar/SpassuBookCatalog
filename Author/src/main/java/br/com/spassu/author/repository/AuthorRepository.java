package br.com.spassu.author.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.spassu.author.model.AuthorModel;

public interface AuthorRepository extends JpaRepository<AuthorModel, UUID>, JpaSpecificationExecutor<AuthorModel> {

	Optional<AuthorModel> findByName(String name);

	List<AuthorModel> findAll(Specification<AuthorModel> spec);
}
