package br.com.spassu.book.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.spassu.book.model.BookAuthorModel;

public interface BookAuthorRepository extends JpaRepository<BookAuthorModel, UUID>   {

    @Query(value="select * from book_author where book_id = :bookId", nativeQuery = true)
    List<BookAuthorModel> findAllCourseUserIntoCourse(@Param("bookId") UUID bookId);
}
