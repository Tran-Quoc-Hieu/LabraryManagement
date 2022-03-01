package com.example.domain.author.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="author")
public class MAuthor {
	@Id
	private Integer authorId;
	private String authorName;
	private Integer authorYearBirthday;
	private String authorNationaty;
}
