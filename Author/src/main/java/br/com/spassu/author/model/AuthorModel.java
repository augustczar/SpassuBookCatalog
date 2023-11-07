package br.com.spassu.author.model;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;

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
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "AUTHOR")
public class AuthorModel implements Serializable {

	private static final long serialVersionUID = 1951223814151494644L;

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID authorId;

	@Column(nullable = false, unique = true, length = 40)
	private String name;

}
