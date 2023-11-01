package br.com.spassu.book.model;

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
public class BookModel {

	private UUID bookId;
	
	private String title;
	
	private String publishingCompany;
	
	private Integer edition;
	
	private String yearPublication;
	
	private BigDecimal price;
}
