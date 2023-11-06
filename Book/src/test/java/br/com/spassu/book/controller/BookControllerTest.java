package br.com.spassu.book.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
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

import br.com.spassu.book.model.BookModel;
import br.com.spassu.book.repository.BookRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private BookRepository bookRepository;
	
	private BookModel bookModel;
	
	private static String TITLE = "Código limpo";
	
	private static String PUBLISHING_COMPANY = "Alta Books";
	
	private static Integer EDITION = 2009;
	
	private static String YEAR_PUBLICATION;
	 
	private static BigDecimal PRICE = new BigDecimal(101.69);
	
	@BeforeEach
	void setUp() throws Exception {
		bookModel = new BookModel();
		bookModel.setBookId(UUID.randomUUID());
		bookModel.setTitle(TITLE);
		bookModel.setPublishingCompany(PUBLISHING_COMPANY);
		bookModel.setEdition(EDITION);
		bookModel.setYearPublication(YEAR_PUBLICATION);
		bookModel.setPrice(PRICE);

		Mockito.when(bookRepository.findByTitle(TITLE))
				.thenReturn(Optional.empty());		
	}
	
	@Test
	void bookGetAllTest() throws Exception {
		mockMvc.perform(get("/books"))
		.andExpect(status().isOk());
	}

	@Test
	void mustFindBookById() throws JsonProcessingException, Exception {
		mockMvc.perform(get("/books/"+ bookModel.getBookId() +"/search")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(bookModel)))
				.andExpect(status().isOk());
	}
	
	@Test
	 void bookSaveTest() throws JsonProcessingException, Exception {
		mockMvc.perform(post("/books")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(bookModel)))
				.andExpect(status().isOk());
	}

	@Test
	void mustUpdateBook() throws JsonProcessingException, Exception {
		mockMvc.perform(put("/books/"+ bookModel.getBookId() +"/edit")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(bookModel)))
				.andExpect(status().isOk());
	}
	
	@Test
	void mustDeleteBook() throws JsonProcessingException, Exception {
		mockMvc.perform(delete("/books/"+ bookModel.getBookId() +"/delete"))
				.andExpect(status().isOk());
	}
}
