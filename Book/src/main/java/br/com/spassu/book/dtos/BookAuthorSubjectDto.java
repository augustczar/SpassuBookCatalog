package br.com.spassu.book.dtos;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookAuthorSubjectDto {

	private UUID bookAuthorSubjectId;

	private String title;

	private String publishingCompany;

	private Integer edition;
	
	private String yearPublication;

	private BigDecimal price;

	private AuthorDto authorDto;

	private SubjectDto subjectDto;

	public BookAuthorSubjectDto bASDto() {
		return new BookAuthorSubjectDto(
				this.bookAuthorSubjectId,
				this.title,
				this.publishingCompany,
				this.edition,
				this.yearPublication,
				this.price,
				this.authorDto,
				this.subjectDto
				);
	}
}
