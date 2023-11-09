package br.com.spassu.subject.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.spassu.subject.model.SubjectBookModel;

public interface SubjectBookRepository extends JpaRepository<SubjectBookModel, UUID> {

}
