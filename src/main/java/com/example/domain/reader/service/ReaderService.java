package com.example.domain.reader.service;

import java.util.List;

import com.example.domain.reader.model.MReader;
import com.example.domain.reader.model.Reader;
import com.example.form.ReaderForm;

public interface ReaderService {
	
	public void addUser(MReader user);
	
	public List<MReader> getAll(MReader form);
	
	public MReader getReader(Integer id);
	
	public Reader findReader(Integer id);
	
	public void updateReader(MReader reader);
	
	public int deleteReader(Integer id);
	
	public void addReader(MReader reader);

	public void updatePasswordReader(MReader map);

	public MReader getLogin(String email);
}
