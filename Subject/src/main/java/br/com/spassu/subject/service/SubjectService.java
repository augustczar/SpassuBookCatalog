package br.com.spassu.subject.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.spassu.subject.model.SubjectModel;

public interface SubjectService {

	SubjectModel save(SubjectModel subjectModel) throws Exception;

	Optional<SubjectModel> findByDescription(String description);

	Optional<SubjectModel> findById(UUID sibjectId);
	
	void delete(SubjectModel subjectModel);

	List<SubjectModel> findAll();

}
