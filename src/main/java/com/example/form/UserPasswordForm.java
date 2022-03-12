package com.example.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class UserPasswordForm {
	private Integer readerId;
	@NotBlank
	@Length(min = 4, max = 25)
	@Pattern(regexp = "^[a-zA-Z0-9]+$" )
	private String password;
}
