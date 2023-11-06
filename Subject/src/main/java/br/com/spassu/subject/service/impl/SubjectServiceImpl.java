package br.com.spassu.subject.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spassu.subject.model.SubjectModel;
import br.com.spassu.subject.repository.SubjectRepository;
import br.com.spassu.subject.service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	private SubjectRepository subjectRepository;
	
	@Override
	public SubjectModel save(SubjectModel subjectModel) throws Exception {
		Optional<SubjectModel> existedSubject  = subjectRepository.findBySubject(subjectModel.getDescription()); 
		
		if (existedSubject.isPresent()) {
			throw new Exception("Subject já existe!", null);
		}
		return  subjectRepository.save(subjectModel);
	}

	@Override
	public Optional<SubjectModel> findBySubject(String description) {
		Optional<SubjectModel> existedSubject = subjectRepository.findBySubject(description);
		return Optional.of(existedSubject.orElse(null));
	}

	@Override
	public Optional<SubjectModel> findById(UUID sibjectId) {
		return subjectRepository.findById(sibjectId);
	}

}
