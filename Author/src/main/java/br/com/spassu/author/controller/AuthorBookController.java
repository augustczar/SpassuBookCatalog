package br.com.spassu.author.controller;

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

import br.com.spassu.author.clients.BookClient;
import br.com.spassu.author.dtos.BookDto;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/authors")
public class AuthorBookController {
	
	@Autowired
	BookClient bookClient;

	@GetMapping("/{authorId}/books")
	public ResponseEntity<List<BookDto>> getAllBooksByAuthor(@PathVariable(value = "authorId") UUID authorId) throws JsonProcessingException {
		return ResponseEntity.status(HttpStatus.OK).body(bookClient.getAllBookByAuthor(authorId));
	}
}
