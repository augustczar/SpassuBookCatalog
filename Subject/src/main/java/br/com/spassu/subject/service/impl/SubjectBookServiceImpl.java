package br.com.spassu.subject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.spassu.subject.repository.SubjectBookRepository;
import br.com.spassu.subject.service.SubjectBookService;

public class SubjectBookServiceImpl implements SubjectBookService {

	@Autowired
	SubjectBookRepository subjectBookRepository;
}
