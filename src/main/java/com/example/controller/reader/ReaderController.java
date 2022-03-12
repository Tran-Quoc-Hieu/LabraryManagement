package com.example.controller.reader;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.reader.model.MReader;
import com.example.domain.reader.service.ReaderService;
import com.example.form.ReaderForm;
import com.example.form.ReaderUpForm;
import com.example.form.UserPasswordForm;
import com.example.form.UserSearchForm;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/reader")
public class ReaderController {
	
	@Autowired
	private ReaderService service;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping("/list")
	public String getListReader(Model model, @ModelAttribute UserSearchForm form) {
		MReader reader = new MReader();
		reader.setReaderAddress(form.getAddress());
		reader.setReaderName(form.getUserName());
		List<MReader> readerList = service.getAll(reader);
		model.addAttribute("readerList", readerList);
		model.addAttribute("readerForm", new ReaderForm());
		return "content/reader/listReader";
	}
	
//	@GetMapping("/detail/{readerId:.+}")
//	public String getReader(Model model, @PathVariable("readerId") Integer id) {
//		MReader reader = service.getReader(id);
//		model.addAttribute("reader", reader);
//		model.addAttribute("readerUpForm", mapper.map(reader, ReaderUpForm.class));
//		UserPasswordForm user = new UserPasswordForm();
//		user.setReaderId(id);
//		model.addAttribute("userPasswordForm", user);
//		return "content/reader/detailReader";
//	}
}
