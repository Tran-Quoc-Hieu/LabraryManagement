package com.example.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.controller.RestResult;
import com.example.domain.book.model.MBook;
import com.example.domain.br.model.BrKey;
import com.example.domain.br.model.MBr;
import com.example.domain.br.service.BrService;
import com.example.domain.reader.model.MReader;
import com.example.domain.reader.service.ReaderService;

@RestController
@RequestMapping("/user")
public class UserRestController {
	@Autowired
	private BrService brService;
	
	@Autowired
	private ReaderService readerService;
	
	@PostMapping("/add")
	public RestResult postBr(MBook form) {
		Authentication ath = SecurityContextHolder.getContext().getAuthentication();
		MReader user = readerService.getLogin(ath.getName());
		BrKey key = new BrKey(user.getReaderId(), form.getBookId());
		MBr br = brService.getBr(key);
		if (br == null || (br.getBrDateReturn() != null && br.getBrDateBorrow() != null)) {
			brService.addMBr(key);
			return new RestResult(0, null);
		}
		return new RestResult(10, null);
	}
}
