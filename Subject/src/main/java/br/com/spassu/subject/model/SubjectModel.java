package br.com.spassu.subject.model;

import java.io.Serializable;
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
@Table(name = "SUBJECT")
public class SubjectModel implements Serializable{
	
	private static final long serialVersionUID = 4209551315772850551L;

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID subjectId;
	
	@Column(nullable = false, unique = true, length = 20)
	private String description;
	
 	@JsonProperty(access = Access.WRITE_ONLY)
	@OneToMany(mappedBy = "subjectBook", fetch = FetchType.LAZY)
	private Set<SubjectBookModel> subjectBooks;
}
