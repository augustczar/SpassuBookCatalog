package br.com.spassu.author.specifications;

import java.util.UUID;

import javax.persistence.criteria.Join;

import org.springframework.data.jpa.domain.Specification;

import br.com.spassu.author.model.AuthorBookModel;
import br.com.spassu.author.model.AuthorModel;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

public class SpecificationTemplate {
	
	@And({
		@Spec(path = "name", spec = LikeIgnoreCase.class)
	})

	public interface AuthorSpec extends Specification<AuthorModel>{}
	
	public static Specification<AuthorModel> authorBookId(final UUID bookId){
		return (root, query, cb) ->{
			query.distinct(true);
			Join<AuthorModel, AuthorBookModel> authorBase = root.join("authorBooks");
			return cb.equal(authorBase.get("bookId"), bookId);
		};
	}
}
