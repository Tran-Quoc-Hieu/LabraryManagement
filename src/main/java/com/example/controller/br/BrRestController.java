package com.example.controller.br;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.br.service.BrService;
import com.example.form.AddBrForm;
import com.example.form.BorrowReturnForm;

@RestController
@RequestMapping("/br")
public class BrRestController {
	@Autowired
	private BrService service;
	
	@DeleteMapping("/delete")
	public int deleteCard(@ModelAttribute BorrowReturnForm form) {
		return service.delete(form);
	}
	@PutMapping("/update")
	public int updateCard(@ModelAttribute BorrowReturnForm form) {
		service.update(form);
		return 0;
	}
	@PutMapping("/choose")
	public int chooseCard(@ModelAttribute AddBrForm form) {
		service.addBook(form);
		return 0;
	}
}
