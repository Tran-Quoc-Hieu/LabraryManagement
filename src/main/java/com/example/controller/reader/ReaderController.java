package com.example.controller.reader;

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

import com.example.domain.reader.model.MReader;
import com.example.domain.reader.service.ReaderService;
import com.example.form.ReaderForm;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/reader")
@Slf4j
public class ReaderController {
	
	@Autowired
	private ReaderService service;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping("/list")
	public String getListReader(Model model,@ModelAttribute ReaderForm form) {
		List<MReader> readerList = service.getAll(form);
		model.addAttribute("readerList", readerList);
		return "content/listReader";
	}
	
	@PostMapping("/list")
	public String postListReader(Model model,@ModelAttribute ReaderForm form) {
		List<MReader> readerList = service.getAll(form);
		model.addAttribute("readerList", readerList);
		return "content/listReader";
	}
	
	@GetMapping("/add")
	public String addReader(@ModelAttribute ReaderForm form) {
		return "content/addReader";
	}
	
	@PostMapping("/add")
	public String createReader(Model model, @ModelAttribute @Validated ReaderForm form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return addReader(form);
		}
		MReader reader = mapper.map(form, MReader.class);
		service.addReader(reader);
		log.info(form.toString());
		return "redirect:/reader/list";
	}
	
	@GetMapping("/detail/{readerId:.+}")
	public String getReader(Model model, @PathVariable("readerId") Integer id) {
		MReader reader = service.getReader(id);
		model.addAttribute("reader", reader);
		return "content/detailReader";
	}
	
	@GetMapping("/detail/update/{readerId:.+}")
	public String updateReader(Model model, @PathVariable("readerId") Integer id) {
		MReader reader = service.getReader(id);
		model.addAttribute("readerForm", mapper.map(reader, ReaderForm.class));
		return "content/updateReader";
	}
	
	@GetMapping("/update/password/{readerId}")
	public String updatePasssReader(Model model, @PathVariable("readerId") Integer id) {
		MReader reader = service.getReader(id);
		reader.setReaderPassword(null);
		model.addAttribute("readerForm", mapper.map(reader, ReaderForm.class));
		return "content/updatePasswordReader";
	}
}
