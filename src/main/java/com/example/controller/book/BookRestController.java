package com.example.controller.book;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.book.model.MBook;
import com.example.domain.book.service.BookService;
import com.example.form.BookForm;

@RestController
@RequestMapping("/book")
public class BookRestController {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private ModelMapper mapper;
	
	@DeleteMapping("/delete")
	public int deleteBook(BookForm form) {
		bookService.deleteBook(form.getBookId());
		return 0;
	}
	
	@PutMapping("/update")
	public int updateBook(BookForm form) {
		bookService.updateBook(mapper.map(form, MBook.class));
		return 0;
	}
}
