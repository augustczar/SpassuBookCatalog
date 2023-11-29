package br.com.spassu.author.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.spassu.author.dtos.AuthorDto;
import br.com.spassu.author.model.AuthorModel;
import br.com.spassu.author.service.AuthorService;
import br.com.spassu.author.specifications.SpecificationTemplate;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/authors")
public class AuthorController {
	
	@Autowired
	AuthorService authorService;
	
 	@GetMapping
	public ResponseEntity<List<AuthorModel>> getAllAuthors(@RequestParam(required = false) UUID bookId) {
	
		if (bookId != null) {
			return ResponseEntity.status(HttpStatus.OK).body(authorService.findAll(SpecificationTemplate.authorBookId(bookId)));			
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(authorService.findAll());
		}
		
	}

	@PostMapping
	public ResponseEntity<Object> saveBook(@RequestBody @Valid AuthorDto authorDto) throws Exception {
		var authorName = authorService.findByName(authorDto.getName());
	
		if (authorName.isPresent()) {
			log.warn("authorName {} this author is already registered!", authorDto.getName());
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: authorName, this author is already registered!");
		}

		var authorModel = new AuthorModel();
		BeanUtils.copyProperties(authorDto, authorModel);
		authorService.save(authorModel);
		log.debug("POST saveAuthor authorId saved {} ", authorModel.getAuthorId());
        log.info("Author saved successfully authorId {} ", authorModel.getAuthorId());
		return ResponseEntity.status(HttpStatus.CREATED).body(authorModel);
	}
	
	@GetMapping("/{authorId}/search")
	public ResponseEntity<Object> getOneBook(@PathVariable(value = "authorId") UUID authorId) {
		Optional<AuthorModel> authorModelOptional = authorService.findById(authorId);
		if (!authorModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author Not Found!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(authorModelOptional.get());
	}
	
	@PutMapping("/{authorId}/edit")
	public ResponseEntity<Object> updateBook(@PathVariable(value = "authorId") UUID authorId,
			@RequestBody @Valid AuthorDto authorDto) throws Exception {
		
		log.debug("PUT updateAuthor authorDto received {} ", authorDto.toString());
		Optional<AuthorModel> authorModelOptional = authorService.findById(authorId);
 		if (!authorModelOptional.isPresent()) {
 			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author Not Found!");
		}
 		
		var authorModel = authorModelOptional.get();
		authorModel.setName(authorDto.getName());
		authorService.save(authorModel);
		log.debug("PUT updateAuthor authorId saved {} ", authorModel.getAuthorId());
        log.info("Author saved successfully authorId {} ", authorModel.getAuthorId());
		
		return ResponseEntity.status(HttpStatus.OK).body(authorModel);
	}
	
	@DeleteMapping("/{authorId}/delete")
	public ResponseEntity<Object> deleteBook(@PathVariable(value = "authorId") UUID authorId) {
		log.debug("DELETE deleteAuthor authorId received {} ", authorId);
		Optional<AuthorModel> authorModelOptional = authorService.findById(authorId);
 		if (!authorModelOptional.isPresent()) {
 			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author Not Found!");
		}
		
 		authorService.delete(authorModelOptional.get());
 		log.debug("DELETE deleteAuthor auhtorId deleted {} ", authorId);
        log.info("Author deleted successfully authorId {} ", authorId);
		return ResponseEntity.status(HttpStatus.OK).body("Author deleted success!");
	}
}
