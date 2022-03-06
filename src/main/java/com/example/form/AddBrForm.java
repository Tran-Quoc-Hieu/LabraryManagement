package com.example.form;

import java.util.List;


import lombok.Data;

@Data
public class AddBrForm {
	private ReaderForm reader;
	private List<BookForm> bookList;
}
