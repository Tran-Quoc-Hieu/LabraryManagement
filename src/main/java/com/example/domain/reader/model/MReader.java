package com.example.domain.reader.model;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.domain.br.model.MBr;

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
	private String role;
	@OneToMany
	@JoinColumn(insertable = false, updatable = false, name = "readerId")
	private List<MBr> readerBrList;
}
