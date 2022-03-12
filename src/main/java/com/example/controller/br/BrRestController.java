package com.example.controller.br;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.controller.RestResult;
import com.example.domain.br.model.BrKey;
import com.example.domain.br.model.MBr;
import com.example.domain.br.service.BrService;
import com.example.form.BorrowReturnForm;
import com.example.form.BrNew;

@RestController
@RequestMapping("/br")
public class BrRestController {
	@Autowired
	private BrService service;
	
	@PostMapping("/add")
	public RestResult addBr(BrNew form) {
		BrKey key = new BrKey(form.getReaderId(), form.getBookId());
		MBr br = service.getBr(key);
		if (br == null || (br.getBrDateReturn() != null && br.getBrDateBorrow() != null)) {
			service.addMBr(key);
			return new RestResult(0, null);
		}
		return new RestResult(10, null);
	}
	
	@DeleteMapping("/delete")
	public int deleteCard(@ModelAttribute BorrowReturnForm form) {
		return service.delete(form);
	}
	@PutMapping("/update")
	public int updateCard(@ModelAttribute BorrowReturnForm form) {
		service.update(form);
		return 0;
	}
}
