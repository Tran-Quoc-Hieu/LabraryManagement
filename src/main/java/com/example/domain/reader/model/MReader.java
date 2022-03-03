package com.example.domain.reader.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="reader")
public class MReader {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer readerId;
	private String readerEmail;
	private String readerPassword;
	private String readerName;
	private String readerAddress;
}
