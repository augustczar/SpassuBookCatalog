	package br.com.spassu.book.clients;

import java.util.List;
import java.util.UUID;

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
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class AuthorClient {

	@Autowired
	RestTemplate restTemplate;

	String REQUEST_URL = "Http://localhost:8087";
	
	ResponseEntity<List<AuthorDto>> result;
	
	Gson gson = new Gson();
	
	public List<AuthorDto> getAllAutorByBook(UUID bookId){

		String url = REQUEST_URL + "/authors?bookId=" + bookId;
		
		log.debug("Request URL : {} ", url);
		log.info("Request URL : {} ", url);
		
		try {
			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter()); 

			ParameterizedTypeReference<List<AuthorDto>> responseType = new ParameterizedTypeReference<List<AuthorDto>>() {};
		
			result = restTemplate.exchange(url, HttpMethod.GET, null, responseType);

			result.getBody();
			
			log.debug("Response Number of Elements: {} ", result.getBody().size());
		} catch (HttpStatusCodeException e) {
			
			log.error("Error request / auhtors {}", e);
		}
		
		log.info("Ending request / authors bookId {} ", bookId);
		return  result.getBody();
	}
}
