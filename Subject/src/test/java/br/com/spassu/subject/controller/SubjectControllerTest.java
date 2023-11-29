package br.com.spassu.subject.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.spassu.subject.model.SubjectModel;
import br.com.spassu.subject.repository.SubjectRepository;

@SpringBootTest
@AutoConfigureMockMvc
class SubjectControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private SubjectRepository subjectRepository;

	private SubjectModel subjectModel;
	
	private static String DESCRIPTION = "Arquitetura limpa";
	
	@BeforeEach
	void setUp() throws Exception {
		subjectModel = new SubjectModel();
		subjectModel.setSubjectId(UUID.randomUUID());
		subjectModel.setDescription(DESCRIPTION);

		Mockito.when(subjectRepository.findByDescription(DESCRIPTION))
				.thenReturn(Optional.empty());
	}
	
	@Test
	void subjectGetAllTest() throws Exception {
		mockMvc.perform(get("/subjects"))
		.andExpect(status().isOk());
	}

	@Test
	void mustFindSubjectById() throws JsonProcessingException, Exception {
		mockMvc.perform(get("/subjects/"+ subjectModel.getSubjectId() +"/search")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(subjectModel)))
				.andExpect(status().isOk());
	}
	
	@Test
	 void subjectSaveTest() throws JsonProcessingException, Exception {
		mockMvc.perform(post("/subjects")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(subjectModel)))
				.andExpect(status().isOk());
	}

	@Test
	void mustUpdateSubject() throws JsonProcessingException, Exception {
		mockMvc.perform(put("/subjects/"+ subjectModel.getSubjectId() +"/edit")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(subjectModel)))
				.andExpect(status().isOk());
	}
	
	@Test
	void mustDeleteSubject() throws JsonProcessingException, Exception {
		mockMvc.perform(delete("/subjects/"+ subjectModel.getSubjectId() +"/delete"))
				.andExpect(status().isOk());
	}

}
