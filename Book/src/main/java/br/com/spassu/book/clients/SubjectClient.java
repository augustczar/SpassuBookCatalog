package br.com.spassu.book.clients;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import br.com.spassu.book.dtos.SubjectDto;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class SubjectClient {

	@Autowired
	RestTemplate restTemplate;

	String REQUEST_URL = "Http://localhost:8092";
	
	public SubjectDto getAllSubjectByBook(UUID bookId) {
		List<SubjectDto> searchResult = null;
		String url = REQUEST_URL + "/matters?bookId" + bookId;
		
		log.debug("Request URL : {} ", url);
		log.info("Request URL : {} ", url);
		
		try {
			
			log.debug("Response Number of Elements: {} ", searchResult.size());
		} catch (HttpStatusCodeException e) {
			
			log.error("Error request / matters {}", e);
		}
		
		log.info("Ending request / matthers bookId {} ", bookId);
		return null;
	}
}
