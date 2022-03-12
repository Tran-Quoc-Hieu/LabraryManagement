package com.example.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ForgotForm {
	@Email
	@NotBlank
	private String email;
}
