package com.example.controller.book;

import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.controller.RestResult;
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
	
	@Autowired
	private MessageSource messageSource;
	
	@PostMapping("/add")
	public RestResult postBook(@Validated BookForm form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new HashMap<String, String>();
			
			for (FieldError error : bindingResult.getFieldErrors()) {
				String message = messageSource.getMessage(error, null);
				errors.put(error.getField(), message);
			}
			return new RestResult(90, errors);
		}
		bookService.addBook(mapper.map(form, MBook.class));
		return new RestResult(0, null);
	}
	
	@DeleteMapping("/delete")
	public int deleteBook(BookForm form) {
		return bookService.deleteBook(form.getBookId());
	}
	
	@PutMapping("/update")
	public RestResult updateBook(@Validated BookForm form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new HashMap<String, String>();
			
			for (FieldError error : bindingResult.getFieldErrors()) {
				String message = messageSource.getMessage(error, null);
				errors.put(error.getField(), message);
			}
			return new RestResult(90, errors);
		}
		bookService.updateBook(mapper.map(form, MBook.class));
		return new RestResult(0, null);
	}
}
