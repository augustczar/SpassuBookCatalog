package br.com.spassu.book.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.spassu.book.dtos.SubjectDto;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/books")
public class BookAuthorSubjectController {

	@GetMapping("/{bookId}/authors")
	public ResponseEntity<List<SubjectDto>> getAllBooksAuthorSubject(@PathVariable(value = "bookId") UUID authorId) {
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
}
