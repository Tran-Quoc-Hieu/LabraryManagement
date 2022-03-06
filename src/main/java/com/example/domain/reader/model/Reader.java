package com.example.domain.reader.model;

import lombok.Data;

@Data
public class Reader {
	private Integer readerId;
	private String readerEmail;
	private String readerPassword;
	private String readerName;
	private String readerAddress;

}
