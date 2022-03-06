package com.example.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class BookForm {
	private Integer bookId;
	@NotBlank
	private String bookName;
	@Max(value = 9999)
	@Min(value = 100)
	@NotNull
	private Integer bookYearPublishing;
	@NotBlank
	private String bookType;
	@NotBlank
	private String bookPublishing;
	@NotBlank
	private String bookAuthor;
	private boolean bookCheck;
}
