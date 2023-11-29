package br.com.spassu.book.clients;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import br.com.spassu.book.dtos.AuthorDto;
import br.com.spassu.book.dtos.BookAuthorSubjectDto;
import br.com.spassu.book.dtos.SubjectDto;
import br.com.spassu.book.model.BookModel;
import br.com.spassu.book.service.BookService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class BookAuthorSubjectClient {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	BookService bookService;

	String REQUEST_URL_AUTHOR = "Http://localhost:8087";
	String REQUEST_URL_SUBJECT = "Http://localhost:8092";
	ResponseEntity<List<AuthorDto>> resultAuthor;
	ResponseEntity<List<SubjectDto>> resultSubject;;

	List<BookAuthorSubjectDto> bookAuthorSubjectDtos;

	public List<BookAuthorSubjectDto> getAllAutorSubjectByBook(UUID bookId) {
		List<Object> result_list = new ArrayList<Object>();

		String urlAuthor = REQUEST_URL_AUTHOR + "/authors?bookId=" + bookId;
		String urlSubject = REQUEST_URL_SUBJECT + "/subjects?bookId=" + bookId;

		log.debug("Request URL : {} ", urlAuthor);
		log.info("Request URL : {} ", urlAuthor);
		log.debug("Request URL : {} ", urlSubject);
		log.info("Request URL : {} ", urlSubject);

		Gson gson = new Gson();
		
		try {

			List<BookModel> bookModelSearch = searchBooks(bookId);
			
			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

			ParameterizedTypeReference<List<AuthorDto>> responseTypeAuthor = new ParameterizedTypeReference<List<AuthorDto>>() {
			};

			resultAuthor = restTemplate.exchange(urlAuthor, HttpMethod.GET, null, responseTypeAuthor);

			resultAuthor.getBody();

			ParameterizedTypeReference<List<SubjectDto>> responseTypeSubject = new ParameterizedTypeReference<List<SubjectDto>>() {
			};

			resultSubject = restTemplate.exchange(urlSubject, HttpMethod.GET, null, responseTypeSubject);

			resultSubject.getBody();

			result_list = Stream.concat(resultAuthor.getBody().stream(), resultSubject.getBody().stream())
					.collect(Collectors.toList());

			Collection<Object> outputList = Stream.concat(bookModelSearch.stream(), result_list.stream())
					.collect(Collectors.toList());

			Object json = gson.toJson(outputList, List.class);

			bookAuthorSubjectDtos = gson.fromJson((String) json, List.class);

			log.debug("Response Number of Elements: {} ", resultAuthor.getBody().size());
			log.debug("Response Number of Elements: {} ", resultSubject.getBody().size());
		} catch (HttpStatusCodeException e) {

			log.error("Error request / auhtors {}", e);
		}

		log.info("Ending request / authors bookId {} ", bookId);
		return bookAuthorSubjectDtos;
	}

	private List<BookModel> searchBooks(UUID bookId) {
		List<BookModel> bookModels;

		if (bookId != null) {
			Optional<BookModel> bookModelOptional = bookService.findById(bookId);

			/*
			 * Optional<Optional<BookModel>> filteredList = Optional.of(bookModelOptional);
			 * List<BookModel> filteredLists = filteredList.stream()
			 * .flatMap(Optional::stream) .collect(Collectors.toList()); return
			 * filteredLists;
			 */

			return bookModelOptional.isPresent() ? Collections.singletonList(bookModelOptional.get())
					: Collections.emptyList();

		}
		bookModels = bookService.findAll();
		return bookModels;
	}
}
