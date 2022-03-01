package com.example.domain.reader.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="reader")
public class MReader {
	@Id
	private Integer readerId;
	private String readerName;
	private String readerAddress;
}
