package com.example.form;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.example.domain.book.model.Book;
import com.example.domain.br.model.MBr;

import lombok.Data;

@Data
public class ReaderForm {

	private Integer readerId;
	@Email
	@NotBlank
	private String readerEmail;
	@Length(max = 25, min = 5)
	@NotBlank
	private String readerPassword;
	@NotBlank
	private String readerName;
	@NotBlank
	private String readerAddress;
	private List<BorrowReturnForm> readerBrList;
	private List<BookForm> listBook;
}
