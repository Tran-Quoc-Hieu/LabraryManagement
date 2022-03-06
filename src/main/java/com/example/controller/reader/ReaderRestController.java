package com.example.controller.reader;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.reader.model.MReader;
import com.example.domain.reader.service.ReaderService;
import com.example.form.ReaderForm;

@RestController
@RequestMapping("/reader")
public class ReaderRestController {
	@Autowired
	private ReaderService readerService;
	
	@Autowired
	private ModelMapper mapper;
	
	@PutMapping("/update")
	public int updateReader(ReaderForm form) {
		readerService.updateReader(mapper.map(form, MReader.class));
		return 0;
	}
	
	@PutMapping("/update/password")
	public int updatePasswordReader(ReaderForm form) {
		readerService.updatePasswordReader(mapper.map(form, MReader.class));
		return 0;
	}
	
	@DeleteMapping("/delete")
	public int deleteReader(@ModelAttribute ReaderForm form) {
		return readerService.deleteReader(form.getReaderId());
	}
}
