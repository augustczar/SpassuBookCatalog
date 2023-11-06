package br.com.spassu.author.controller;

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

import br.com.spassu.author.model.AuthorModel;
import br.com.spassu.author.repository.AuthorRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthorControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private AuthorRepository authorRepository;

	private AuthorModel authorModel;
	
	private static String NAME = "Robert C. Martin";
	
	@BeforeEach
	void setUp() throws Exception {
		authorModel = new AuthorModel();
		authorModel.setAuthorId(UUID.randomUUID());
		authorModel.setName(NAME);

		Mockito.when(authorRepository.findByAuthor(NAME))
				.thenReturn(Optional.empty());
	}
	
	@Test
	void authorGetAllTest() throws Exception {
		mockMvc.perform(get("/authors"))
		.andExpect(status().isOk());
	}

	@Test
	void mustFindAuthorById() throws JsonProcessingException, Exception {
		mockMvc.perform(get("/authors/"+ authorModel.getAuthorId() +"/search")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(authorModel)))
				.andExpect(status().isOk());
	}
	
	@Test
	 void authorSaveTest() throws JsonProcessingException, Exception {
		mockMvc.perform(post("/authors")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(authorModel)))
				.andExpect(status().isOk());
	}

	@Test
	void mustUpdateAuthor() throws JsonProcessingException, Exception {
		mockMvc.perform(put("/authors/"+ authorModel.getAuthorId() +"/edit")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(authorModel)))
				.andExpect(status().isOk());
	}
	
	@Test
	void mustDeleteAuthor() throws JsonProcessingException, Exception {
		mockMvc.perform(delete("/authors/"+ authorModel.getAuthorId() +"/delete"))
				.andExpect(status().isOk());
	}
}
