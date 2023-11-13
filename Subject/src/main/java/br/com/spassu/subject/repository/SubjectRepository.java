package br.com.spassu.subject.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.spassu.subject.model.SubjectModel;

public interface SubjectRepository extends JpaRepository<SubjectModel, UUID> {

	Optional<SubjectModel> findByDescription(String description);

	List<SubjectModel> findAll(Specification<SubjectModel> subjectBookId);

}
