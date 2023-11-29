package br.com.spassu.book.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.spassu.book.clients.BookAuthorSubjectClient;
import br.com.spassu.book.dtos.BookAuthorSubjectDto;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/books")
public class BookAuthorSubjectController {
	
	@Autowired
	BookAuthorSubjectClient bookAuthorSubjectClient;

	@GetMapping("/{bookId}/authors/subjects")
	public ResponseEntity<List<BookAuthorSubjectDto>> getAllBooksAuthor(@PathVariable(value = "bookId") UUID bookId) throws JsonProcessingException {
		return ResponseEntity.status(HttpStatus.OK).body(bookAuthorSubjectClient.getAllAutorSubjectByBook(bookId));
	}
}
