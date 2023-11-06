package br.com.spassu.subject.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.spassu.subject.model.SubjectModel;

public interface SubjectRepository extends JpaRepository<SubjectModel, UUID> {

	Optional<SubjectModel> findBySubject(String description);

}
