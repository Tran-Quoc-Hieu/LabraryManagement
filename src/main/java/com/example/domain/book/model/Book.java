package com.example.domain.book.model;

import lombok.Data;

@Data
public class Book {
	private Integer bookId;
	private String bookName;
	private Integer bookYearPublishing;
	private String bookType;
	private String bookPublishing;
	private String bookAuthor;
}
