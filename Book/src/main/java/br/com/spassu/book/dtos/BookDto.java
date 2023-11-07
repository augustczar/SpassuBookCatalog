package br.com.spassu.book.dtos;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
@EqualsAndHashCode
public class BookDto {

	private String title;
	
	@NotBlank
	private String publishingCompany;
	
	@NotBlank
	private Integer edition;
	
	@NotBlank
	private String yearPublication;
	
	@NotNull
	private BigDecimal price;
}
