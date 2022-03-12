package com.example.controller.book;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.book.model.MBook;
import com.example.domain.book.service.BookService;
import com.example.form.BookForm;
import com.example.form.BookSearchForm;

@Controller
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private BookService service;
	
	@Autowired
	private ModelMapper mapper; 
	
	@GetMapping("/list")
	public String getAllBooks(Model model,@ModelAttribute BookSearchForm form) {
		MBook book = new MBook();
		book.setBookAuthor(form.getAuthor());
		book.setBookName(form.getTitle());
		List<MBook> books = service.getAll(book);
		model.addAttribute("bookList", books);
		model.addAttribute("bookForm", new BookForm());
		return "content/book/listBook";
	}
	
	@GetMapping("/detail/{bookId:.+}")
	public String getBook(@PathVariable("bookId") Integer id,Model model) {
		MBook book = service.getBook(id);
		model.addAttribute("book", book);
		model.addAttribute("bookForm", mapper.map(book, BookForm.class));
		return "content/book/detailBook";
	}
}
