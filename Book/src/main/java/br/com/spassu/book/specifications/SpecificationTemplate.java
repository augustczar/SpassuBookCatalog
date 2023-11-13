package br.com.spassu.book.specifications;

import java.util.UUID;

import javax.persistence.criteria.Join;

import org.springframework.data.jpa.domain.Specification;

import br.com.spassu.book.model.BookAuthorModel;
import br.com.spassu.book.model.BookModel;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

public class SpecificationTemplate {
	
	@And({
		@Spec(path = "title", spec = LikeIgnoreCase.class),
		@Spec(path = "publishingCompany", spec = LikeIgnoreCase.class),
		@Spec(path = "yearPublication", spec = LikeIgnoreCase.class)
	})

	public interface BookSpec extends Specification<BookModel>{}
	
	public static Specification<BookModel> bookAuthorId(final UUID authorId){
		return (root, query, cb) ->{
			query.distinct(true);
			Join<BookModel, BookAuthorModel> bookBase = root.join("bookAuthors");
			return cb.equal(bookBase.get("authorId"), authorId);
		};
	}
}
