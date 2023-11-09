package br.com.spassu.author.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

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
@JsonInclude(JsonInclude.Include.NON_NULL)
//@Entity
//@Table(name = "BOOK_AUTHOR")
public class BookAuthor  implements Serializable {

	private static final long serialVersionUID = -2289867307643096844L;

	@ManyToMany(fetch = FetchType.LAZY)
	private AuthorModel author;
	
	@Column(nullable = false)
	private UUID bookId;
}
