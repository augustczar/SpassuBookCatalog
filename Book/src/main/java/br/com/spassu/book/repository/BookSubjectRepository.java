package br.com.spassu.book.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.spassu.book.model.BookSubjectModel;

public interface BookSubjectRepository extends JpaRepository<BookSubjectModel, UUID> {

}
