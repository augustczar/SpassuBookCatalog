package br.com.spassu.author.clients;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import br.com.spassu.author.dtos.BookDto;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class BookClient {

	@Autowired
	RestTemplate restTemplate;

	String REQUEST_URL = "Http://localhost:8082";
	
	List<BookDto> result;

	
	public List<BookDto> getAllBookByAuthor(UUID authorId){
		List<BookDto> searchResult = null;
		String url = REQUEST_URL + "/books?authorId=" + authorId;
		
		log.debug("Request URL : {} ", url);
		log.info("Request URL : {} ", url);
		
		try {

			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter()); 
			
			result = restTemplate.getForObject(url, List.class);

			 searchResult = result;
			
			log.debug("Response Number of Elements: {} ", searchResult.size());
		} catch (HttpStatusCodeException e) {
			
			log.error("Error request / auhtors {}", e);
		}
		
		log.info("Ending request / authors bookId {} ", authorId);
		return result;
	}
	
	public ResponseEntity<BookDto> getOneUserById(UUID bookId){ 
		String url = REQUEST_URL + "/books/" + bookId; 
		return restTemplate.exchange(url, HttpMethod.GET, null, BookDto.class); 
	}
}
