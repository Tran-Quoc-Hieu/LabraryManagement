package com.example.form;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


import lombok.Data;

@Data
public class ReaderUpForm {
	private Integer readerId;
	@Email
	@NotBlank
	private String readerEmail;
	private String readerPassword;
	@NotBlank
	private String readerName;
	@NotBlank
	private String readerAddress;
	private List<BorrowReturnForm> readerBrList;
	private List<BookForm> listBook;
}
