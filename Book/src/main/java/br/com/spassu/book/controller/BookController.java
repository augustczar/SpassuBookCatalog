package br.com.spassu.book.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.spassu.book.dtos.BookDto;
import br.com.spassu.book.model.BookModel;
import br.com.spassu.book.service.BookService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/books")
public class BookController {

	@Autowired
	BookService bookService;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<BookModel>> getAllBooks() {
		return ResponseEntity.status(HttpStatus.OK).body(bookService.findAll());
	}

	@PostMapping
	public ResponseEntity<Object> saveBook(@RequestBody @Valid BookDto bookDto) throws Exception {
		var bookTitle = bookService.findByTitle(bookDto.getTitle());
		
		if (bookTitle.isPresent()) {
			log.warn("bookTitle {} you are already registered!", bookDto.getTitle());
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: bookName you are already registered!");
		}

		var bookModel = new BookModel();
		BeanUtils.copyProperties(bookDto, bookModel);
		bookService.save(bookModel);
		log.debug("POST saveBook bookId saved {} ", bookModel.getBookId());
        log.info("Book saved successfully bookId {} ", bookModel.getBookId());
		return ResponseEntity.status(HttpStatus.CREATED).body(bookModel);
	}
	
	@GetMapping("/{bookId}/search")
	public ResponseEntity<Object> getBook(@PathVariable(value = "bookId") UUID bookId) {
		Optional<BookModel> bookModelOptional = bookService.findById(bookId);
		if (!bookModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book Not Found!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(bookModelOptional.get());
	}
	
	@PutMapping("/{bookId}/edit")
	public ResponseEntity<Object> updateBook(@PathVariable(value = "bookId") UUID bookId,
			@RequestBody @Valid BookDto bookDto) throws Exception {
		
		log.debug("PUT updateBook bookDto received {} ", bookDto.toString());
		Optional<BookModel> bookModelOptional = bookService.findById(bookId);
 		if (!bookModelOptional.isPresent()) {
 			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book Not Found!");
		}
 		
		var bookModel = bookModelOptional.get();
		bookModel.setTitle(bookDto.getTitle());
		bookModel.setPublishingCompany(bookDto.getPublishingCompany());
		bookModel.setEdition(bookDto.getEdition());
		bookModel.setYearPublication(bookDto.getYearPublication());
		bookModel.setPrice(bookDto.getPrice());
		
		bookService.save(bookModel);
		log.debug("PUT updateBook bookId saved {} ", bookModel.getBookId());
        log.info("Book saved successfully bookId {} ", bookModel.getBookId());

		return ResponseEntity.status(HttpStatus.OK).body(bookModel);
	}
	
	@DeleteMapping("/{bookId}/delete")
	public ResponseEntity<Object> deleteBook(@PathVariable(value = "bookId") UUID bookId) {
		log.debug("DELETE deleteBook bookId received {} ", bookId);
		Optional<BookModel> bookModelOptional = bookService.findById(bookId);
 		if (!bookModelOptional.isPresent()) {
 			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book Not Found!");
		}
		
 		bookService.delete(bookModelOptional.get());
 		log.debug("DELETE deleteBook bookId deleted {} ", bookId);
        log.info("Book deleted successfully bookId {} ", bookId);
		return ResponseEntity.status(HttpStatus.OK).body("Book deleted success!");
	}
}
