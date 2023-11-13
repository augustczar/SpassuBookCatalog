package br.com.spassu.author.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.spassu.author.model.AuthorBookModel;
import br.com.spassu.author.model.AuthorModel;

public interface AuthorBookRepository  extends JpaRepository<AuthorBookModel, UUID> {

    boolean existsByAuthorAndBookId(AuthorModel authorModel, UUID bookId);

    @Query(value="select * from author_books where authori_id = :authorId", nativeQuery = true)
    List<AuthorBookModel> findAllAuthorBookIntoAuthor(@Param("authorId") UUID authorId);
}
