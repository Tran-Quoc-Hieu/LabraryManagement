package com.example.controller.br;

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
import com.example.domain.br.model.BrKey;
import com.example.domain.br.model.MBr;
import com.example.domain.br.service.BrService;
import com.example.domain.reader.model.MReader;
import com.example.domain.reader.service.ReaderService;
import com.example.form.BorrowReturnForm;
import com.example.form.BrNew;
import com.example.form.BrSearchForm;

@Controller
@RequestMapping("/br")
public class BrController {
	@Autowired
	private BrService brService;
	
	@Autowired
	private ReaderService readerService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping("/list")
	public String getListBr(Model model, @ModelAttribute BrSearchForm form) {
		List<MBr> brList = brService.getMany(form);
		model.addAttribute("brList", brList);
		List<MBook> bookList = bookService.getAll(new MBook());
		model.addAttribute("bookList", bookList);
		List<MReader> readerList = readerService.getAll(new MReader());
		model.addAttribute("readerList", readerList);
		model.addAttribute("brNew", new BrNew());
		return "content/br/listBr";
	}
	
	@GetMapping("/detail/readerId={readerId:.+}&bookId={bookId:.+}")
	public String getDetail(Model model,@PathVariable("readerId") Integer readerId, @PathVariable("bookId") Integer bookId) {
		BrKey key = new BrKey(readerId, bookId);
		MBr br = brService.getBr(key);
		BorrowReturnForm form = mapper.map(br, BorrowReturnForm.class);
		model.addAttribute("borrowReturnForm", form);
		return "content/br/detailBr";
	}
}
