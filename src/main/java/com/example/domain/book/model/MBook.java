package com.example.domain.book.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.domain.br.model.MBr;

import lombok.Data;

@Data
@Entity
@Table(name="book")
public class MBook {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookId;
	private String bookName;
	private Integer bookYearPublishing;
	private String bookType;
	private String bookPublishing;
	private String bookAuthor;
	@OneToMany
	@JoinColumn(insertable = false, updatable = false, name="bookId")
	private List<MBr> bookBrList;
}
