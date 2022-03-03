package com.example.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class ReaderForm {

	private Integer readerId;
	@Email
	private String readerEmail;
	@Length(max = 25, min = 5)
	@NotBlank
	private String readerPassword;
	@NotBlank
	private String readerName;
	@NotBlank
	private String readerAddress;
}
