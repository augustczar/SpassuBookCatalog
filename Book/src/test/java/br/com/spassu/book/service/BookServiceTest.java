package br.com.spassu.book.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.spassu.book.model.BookModel;
import br.com.spassu.book.repository.BookRepository;

@SpringBootTest
class BookServiceTest {

	@MockBean
	private BookRepository bookRepository;
	
	@Autowired
	private	BookService bookService;

	private BookModel bookModel;
	
	private static String TITLE = "Código limpo";
	
	private static String PUBLISHING_COMPANY = "Alta Books";
	
	private static Integer EDITION = 0001;
	
	private static String YEAR_PUBLICATION = "2009";
	 
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
	void mustSaveBookRepository() throws Exception {
		bookService.save(bookModel);

		verify(bookRepository).save(bookModel);
	}
	
	@Test
	void DoNotSaveBookWithTheSameTitle() {
		Assertions.assertThrows(Exception.class, () -> {
			bookService.findByTitle(TITLE);
			 });
		Assertions.assertNull(null, "Livro já existe!");
		
	}
	
	@Test
	void whenSearchingByIdReturnsABook() {
		Mockito.when(bookRepository.findById(bookModel.getBookId())).thenReturn(Optional.of(bookModel));
		
		Optional<BookModel> resBookModel = bookService.findById(bookModel.getBookId());
		assertEquals(bookModel, resBookModel.get());
	}
}
