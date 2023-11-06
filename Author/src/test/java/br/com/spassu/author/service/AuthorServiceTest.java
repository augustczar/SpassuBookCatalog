package br.com.spassu.author.service;

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

import br.com.spassu.author.model.AuthorModel;
import br.com.spassu.author.repository.AuthorRepository;

@SpringBootTest
class AuthorServiceTest {

	@MockBean
	private AuthorRepository authorRepository;
	
	@Autowired
	private	AuthorService authorService;

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
	void mustSaveAuthorRepository() throws Exception {
		authorService.save(authorModel);

		verify(authorRepository).save(authorModel);
	}
	
	@Test
	void DoNotSaveAuthorWithTheSameName() {
		Assertions.assertThrows(Exception.class, () -> {
			authorService.findByAuthor(NAME);
			 });
		Assertions.assertNull(null, "Author já existe!");
		
	}
	
	@Test
	void whenSearchingByIdReturnsAAuthor() {
		Mockito.when(authorRepository.findById(authorModel.getAuthorId())).thenReturn(Optional.of(authorModel));
		
		Optional<AuthorModel> resAuthorModel = authorService.findById(authorModel.getAuthorId());
		assertEquals(authorModel, resAuthorModel.get());
	}

}
