package com.example.domain.book.service;

import java.util.List;

import com.example.domain.book.model.Book;
import com.example.domain.book.model.MBook;
import com.example.form.BookForm;

public interface BookService {
	public List<MBook> getAll(MBook form);
	
	public MBook getBook(Integer id);
	
	public Book findBook(Integer id);
	
	public void updateBook(MBook book);
	
	public int deleteBook(Integer id);
	
	public void addBook(MBook book);
	
	public List<BookForm> getBookForm();
}
