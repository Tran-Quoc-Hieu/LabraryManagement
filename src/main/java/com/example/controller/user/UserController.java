package com.example.controller.user;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.br.model.BrKey;
import com.example.domain.br.service.BrService;
import com.example.domain.reader.model.MReader;
import com.example.domain.reader.service.ReaderService;
import com.example.form.BorrowReturnForm;
import com.example.form.ReaderUpForm;
import com.example.form.UserPasswordForm;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private ReaderService service;
	
	@Autowired
	private BrService brService;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping("/detail/{userId:.+}")
	public String getUser(Model model, @PathVariable("userId") Integer id) {
		MReader reader = service.getReader(id);
		model.addAttribute("reader", reader);
		model.addAttribute("readerUpForm", mapper.map(reader, ReaderUpForm.class));
		UserPasswordForm user = new UserPasswordForm();
		user.setReaderId(id);
		model.addAttribute("userPasswordForm", user);
		return "content/reader/detailReader";
	}
	
	@GetMapping("/update/readerId={readerId:.+}&bookId={bookId:.+}")
	public String postBr(@PathVariable("readerId") Integer readerId, @PathVariable("bookId") Integer bookId) {
		BorrowReturnForm br = new BorrowReturnForm();
		br.setBrKey(new BrKey(readerId, bookId));
		brService.update(br);
		return "redirect:/user/detail/"+readerId;
	}
}
