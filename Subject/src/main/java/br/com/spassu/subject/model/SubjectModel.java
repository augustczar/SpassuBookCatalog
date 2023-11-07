package br.com.spassu.subject.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
}
