package com.example.controller.book;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.book.model.MBook;
import com.example.domain.book.service.BookService;
import com.example.form.BookForm;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/book")
@Slf4j
public class BookController {
	
	@Autowired
	private BookService service;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping("/list")
	public String getAllBooks(Model model,@ModelAttribute BookForm form) {
		MBook book = new MBook();
		List<MBook> books = service.getAll(book);
		model.addAttribute("bookList", books);
		List<MBook> bookListTypes = service.getAll(book);
		model.addAttribute("bookListTypes", bookListTypes);
		return "content/listBook";
	}
	
	@PostMapping("/list")
	public String postAllBooks(Model model,@ModelAttribute BookForm form) {
		MBook book = new MBook();
		List<MBook> bookListTypes = service.getAll(book);
		model.addAttribute("bookListTypes", bookListTypes);
		book = mapper.map(form, MBook.class);
		List<MBook> books = service.getAll(book);
		model.addAttribute("bookList", books);
		return "content/listBook";
	}
	
	@GetMapping("/detail/{bookId:.+}")
	public String getBook(@PathVariable("bookId") Integer id,Model model) {
		MBook book = service.getBook(id);
		model.addAttribute("book", book);
		return "content/detailBook";
	}
	
	@GetMapping("/detail/update/{bookId:.+}")
	public String getBookUpdate(@PathVariable("bookId") Integer id,Model model) {
		MBook book = service.getBook(id);
		model.addAttribute("book", book);
		return "content/updateBook";
	}
	
	@GetMapping("/add")
	public String newBook(Model model, @ModelAttribute BookForm form) {
		return "content/addBook";
	}
	
	@PostMapping("/add")
	public String addBook(Model model, @ModelAttribute @Validated BookForm form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return newBook(model, form);
		}
		MBook book = mapper.map(form, MBook.class);
		service.addBook(book);
		log.info(form.toString());
		return "redirect:/book/list";
	}
}
