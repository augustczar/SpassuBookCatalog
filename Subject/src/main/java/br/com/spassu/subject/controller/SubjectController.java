package br.com.spassu.subject.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.spassu.subject.dtos.SubjectDto;
import br.com.spassu.subject.model.SubjectModel;
import br.com.spassu.subject.service.SubjectService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/subjects")
public class SubjectController {
	
	@Autowired
	SubjectService subjectService;

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<SubjectModel>> getAllSubject() {
		return ResponseEntity.status(HttpStatus.OK).body(subjectService.findAll());
	}

	@PostMapping
	public ResponseEntity<Object> saveSubject(@RequestBody @Valid SubjectDto subjectDto) throws Exception {
		var subjectDescription = subjectService.findByDescription(subjectDto.getDescription());
		
		if (subjectDescription.isPresent()) {
			log.warn("authorName {} you are already registered!", subjectDto.getDescription());
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: subjectDescription you are already registered!");
		}

		var subjectModel = new SubjectModel();
		BeanUtils.copyProperties(subjectDto, subjectModel);
		subjectService.save(subjectModel);
		log.debug("POST saveSubject subjectId saved {} ", subjectModel.getSubjectId());
        log.info("Author saved successfully subjectId {} ", subjectModel.getSubjectId());
		return ResponseEntity.status(HttpStatus.CREATED).body(subjectModel);
	}
	
	@GetMapping("/{subjectId}/search")
	public ResponseEntity<Object> getSubject(@PathVariable(value = "subjectId") UUID subjectId) {
		Optional<SubjectModel> subjectModelOptional = subjectService.findById(subjectId);
		if (!subjectModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subject Not Found!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(subjectModelOptional.get());
	}
	
	@PutMapping("/{subjectId}/edit")
	public ResponseEntity<Object> updateSubject(@PathVariable(value = "subjectId") UUID subjectId,
			@RequestBody @Valid SubjectDto subjectDto) throws Exception {
	log.debug("PUT updateSubject subjectDto received {} ", subjectDto.toString());
	Optional<SubjectModel> subjectModelOptional = subjectService.findById(subjectId);
		if (!subjectModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subject Not Found!");
		}
		
		var subjectModel = subjectModelOptional.get();
		subjectModel.setDescription(subjectDto.getDescription());
		subjectService.save(subjectModel);
		log.debug("PUT updateSubject subjectId saved {} ", subjectModel.getSubjectId());
        log.info("Subject saved successfully subjectId {} ", subjectModel.getSubjectId());
		
		
		return ResponseEntity.status(HttpStatus.OK).body(subjectModel);
	}
	
	@DeleteMapping("/{subjectId}/delete")
	public ResponseEntity<Object> deleteSubject(@PathVariable(value = "subjectId") UUID subjectId) {
		log.debug("DELETE deleteSubject subjectId received {} ", subjectId);
		Optional<SubjectModel> subjectModelOptional = subjectService.findById(subjectId);
 		if (!subjectModelOptional.isPresent()) {
 			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subject Not Found!");
		}
		
 		subjectService.delete(subjectModelOptional.get());
 		log.debug("DELETE deleteSubject subjectId deleted {} ", subjectId);
        log.info("Author deleted successfully subjectId {} ", subjectId);
		return ResponseEntity.status(HttpStatus.OK).body("Subject deleted success!");
	}
}
