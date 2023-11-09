package br.com.spassu.book.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "BOOK")
public class BookModel implements Serializable{

	private static final long serialVersionUID = -1588900577343705351L;

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID bookId;
	
	@Column(nullable = false, unique = true, length = 40)
	private String title;
	
	@Column(nullable = false, length = 40)
	private String publishingCompany;
	
	@Column(nullable = false)
	private Integer edition;
	
	@Column(nullable = false, length = 4)
	private String yearPublication;
	
	@Column(nullable = false)
	private BigDecimal price;
	
 	@JsonProperty(access = Access.WRITE_ONLY)
	@OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
	private Set<BookAuthorModel> bookAuthors;

	
}
