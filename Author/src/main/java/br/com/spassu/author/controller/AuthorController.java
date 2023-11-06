package br.com.spassu.author.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.spassu.author.model.AuthorModel;

@RestController
@RequestMapping("/authors")
public class AuthorController {

	@GetMapping
	@ResponseBody
	public String getAll() {
		return "Ok";
	}

	@PostMapping
	public ResponseEntity<AuthorModel> saveBook(AuthorModel authorModel) {
		return ResponseEntity.status(HttpStatus.OK).body(authorModel);
	}
	
	@GetMapping("/{authorId}/search")
	public ResponseEntity<AuthorModel> getBook(AuthorModel authorModel) {
		return ResponseEntity.status(HttpStatus.OK).body(authorModel);
	}
	
	@PutMapping("/{authorId}/edit")
	public ResponseEntity<AuthorModel> updateBook(AuthorModel authorModel) {
		return ResponseEntity.status(HttpStatus.OK).body(authorModel);
	}
	
	@DeleteMapping("/{authorId}/delete")
	public ResponseEntity<Object> deleteBook(AuthorModel authorModel) {
		return ResponseEntity.status(HttpStatus.OK).body("Author deleted success!");
	}
}
