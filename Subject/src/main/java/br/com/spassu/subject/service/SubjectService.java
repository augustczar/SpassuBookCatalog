package br.com.spassu.subject.service;

import java.util.Optional;
import java.util.UUID;

import br.com.spassu.subject.model.SubjectModel;

public interface SubjectService {

	SubjectModel save(SubjectModel subjectModel) throws Exception;

	Optional<SubjectModel> findBySubject(String description);

	Optional<SubjectModel> findById(UUID sibjectId);

}
