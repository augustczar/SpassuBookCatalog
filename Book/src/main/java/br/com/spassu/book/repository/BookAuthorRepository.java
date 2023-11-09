package br.com.spassu.book.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.spassu.book.model.BookAuthorModel;

public interface BookAuthorRepository extends JpaRepository<BookAuthorModel, UUID>   {

}
