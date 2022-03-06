package com.example.form;

import java.util.Date;

import com.example.domain.book.model.Book;
import com.example.domain.br.model.BrKey;
import com.example.domain.reader.model.Reader;

import lombok.Data;

@Data
public class BorrowReturnForm {
	private BrKey brKey;
	private Date brDateBorrow;
	private Date brDateReturn;
	private Book book;
	private Reader reader;
	private boolean check;
}
