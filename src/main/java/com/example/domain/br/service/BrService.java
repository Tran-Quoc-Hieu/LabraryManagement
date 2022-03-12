package com.example.domain.br.service;

import java.util.List;

import com.example.domain.br.model.BrKey;
import com.example.domain.br.model.MBr;
import com.example.form.BorrowReturnForm;
import com.example.form.BrSearchForm;


public interface BrService {
	public List<MBr> getMany(BrSearchForm form);
	
	public MBr getBr(BrKey br);
	
	public void update(BorrowReturnForm form);
	
	public int delete(BorrowReturnForm form);
	
	public void addMBr(BrKey br);
}
