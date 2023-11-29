package br.com.spassu.subject.specifications;

import java.util.UUID;

import javax.persistence.criteria.Join;

import org.springframework.data.jpa.domain.Specification;

import br.com.spassu.subject.model.SubjectBookModel;
import br.com.spassu.subject.model.SubjectModel;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

public class SpecificationTemplate {
	
	@And({
		@Spec(path = "description", spec = LikeIgnoreCase.class)
	})

	public interface SubjectSpec extends Specification<SubjectModel>{}
	
	public static Specification<SubjectModel> subjectBookId(final UUID bookId){
		return (root, query, cb) ->{
			query.distinct(true);
			Join<SubjectModel, SubjectBookModel> subjectBase = root.join("subjectBooks");
			return cb.equal(subjectBase.get("bookId"), bookId);
		};
	}
}
