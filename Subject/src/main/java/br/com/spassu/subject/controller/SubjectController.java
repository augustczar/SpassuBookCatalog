package br.com.spassu.subject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.spassu.subject.model.SubjectModel;

@RestController
@RequestMapping("/matters")
public class SubjectController {

	@GetMapping
	@ResponseBody
	public String getAll() {
		return "Ok";
	}

	@PostMapping
	public ResponseEntity<SubjectModel> saveSubject(SubjectModel subjectModel) {
		return ResponseEntity.status(HttpStatus.OK).body(subjectModel);
	}
	
	@GetMapping("/{subjectId}/search")
	public ResponseEntity<SubjectModel> getSubject(SubjectModel subjectModel) {
		return ResponseEntity.status(HttpStatus.OK).body(subjectModel);
	}
	
	@PutMapping("/{subjectId}/edit")
	public ResponseEntity<SubjectModel> updateSubject(SubjectModel subjectModel) {
		return ResponseEntity.status(HttpStatus.OK).body(subjectModel);
	}
	
	@DeleteMapping("/{subjectId}/delete")
	public ResponseEntity<Object> deleteSubject(SubjectModel subjectModel) {
		return ResponseEntity.status(HttpStatus.OK).body("Subject deleted success!");
	}
}
