package com.example.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class SignupForm {
	@Email
	@NotBlank
	private String readerEmail;
	@NotBlank
	@Length(min = 4, max = 25)
	@Pattern(regexp = "^[a-zA-Z0-9]+$" )
	private String readerPassword;
	@NotBlank
	private String readerName;
	@NotBlank
	private String readerAddress;
}
