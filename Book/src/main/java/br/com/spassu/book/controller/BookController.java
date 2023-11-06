package br.com.spassu.book.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.spassu.book.model.BookModel;

@RestController
@RequestMapping("/books")
public class BookController {

	@GetMapping
	@ResponseBody
	public String getAll() {
		return "Ok";
	}

	@PostMapping
	public ResponseEntity<BookModel> saveBook(BookModel bookModel) {
		return ResponseEntity.status(HttpStatus.OK).body(bookModel);
	}
	
	@GetMapping("/{bookId}/search")
	public ResponseEntity<BookModel> getBook(BookModel bookModel) {
		return ResponseEntity.status(HttpStatus.OK).body(bookModel);
	}
	
	@PutMapping("/{bookId}/edit")
	public ResponseEntity<BookModel> updateBook(BookModel bookModel) {
		return ResponseEntity.status(HttpStatus.OK).body(bookModel);
	}
	
	@DeleteMapping("/{bookId}/delete")
	public ResponseEntity<Object> deleteBook(BookModel bookModel) {
		return ResponseEntity.status(HttpStatus.OK).body("Book deleted success!");
	}
}
