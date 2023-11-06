package br.com.spassu.book.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
}
