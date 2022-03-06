package com.example.domain.br.model;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.example.domain.book.model.Book;
import com.example.domain.reader.model.Reader;

import lombok.Data;

@Data
@Entity
@Table(name = "borrow_return_book")
public class MBr {
	@EmbeddedId
	private BrKey brKey;
	private Date brDateBorrow;
	private Date brDateReturn;
	@Transient
	private Book book;
	@Transient
	private Reader reader;
}
