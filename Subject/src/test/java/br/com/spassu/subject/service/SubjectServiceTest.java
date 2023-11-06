package br.com.spassu.subject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.spassu.subject.model.SubjectModel;
import br.com.spassu.subject.repository.SubjectRepository;

@SpringBootTest
class SubjectServiceTest {

	@MockBean
	private SubjectRepository subjectRepository;
	
	@Autowired
	private	SubjectService subjectService;

	private SubjectModel subjectModel;
	
	private static String DESCRIPTION = "Arquitetura limpa";
	
	@BeforeEach
	void setUp() throws Exception {
		subjectModel = new SubjectModel();
		subjectModel.setSubjectId(UUID.randomUUID());
		subjectModel.setDescription(DESCRIPTION);

		Mockito.when(subjectRepository.findBySubject(DESCRIPTION))
				.thenReturn(Optional.empty());
		
	}
	
	@Test
	void mustSaveSubjectRepository() throws Exception {
		subjectService.save(subjectModel);

		verify(subjectRepository).save(subjectModel);
	}
	
	@Test
	void DoNotSaveSubjectWithTheSameName() {
		Assertions.assertThrows(Exception.class, () -> {
			subjectService.findBySubject(DESCRIPTION);
			 });
		Assertions.assertNull(null, "Assunto já existe!");
		
	}
	
	@Test
	void whenSearchingByIdReturnsASubject() {
		Mockito.when(subjectRepository.findById(subjectModel.getSubjectId())).thenReturn(Optional.of(subjectModel));
		
		Optional<SubjectModel> resSubjectModel = subjectService.findById(subjectModel.getSubjectId());
		assertEquals(subjectModel, resSubjectModel.get());
	}
}
