package br.com.spassu.author.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.spassu.author.model.AuthorBookModel;

public interface AuthorBookRepository  extends JpaRepository<AuthorBookModel, UUID> {

}
