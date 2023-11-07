package br.com.spassu.subject.service.impl;

import java.util.List;
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
		Optional<SubjectModel> existedSubject  = subjectRepository.findByDescription(subjectModel.getDescription()); 
		
		if (existedSubject.isPresent()) {
			throw new Exception("Subject já existe!", null);
		}
		return  subjectRepository.save(subjectModel);
	}

	@Override
	public Optional<SubjectModel> findByDescription(String description) {
		Optional<SubjectModel> existedSubject = subjectRepository.findByDescription(description);
		return Optional.of(existedSubject.orElse(null));
	}

	@Override
	public Optional<SubjectModel> findById(UUID sibjectId) {
		return subjectRepository.findById(sibjectId);
	}

	@Override
	public void delete(SubjectModel subjectModel) {
		subjectRepository.delete(subjectModel);
	}

	@Override
	public List<SubjectModel> findAll() {
		return subjectRepository.findAll();
	}

}
