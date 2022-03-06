package com.example.domain.reader.service;

import java.util.List;

import com.example.domain.reader.model.MReader;
import com.example.domain.reader.model.Reader;
import com.example.form.ReaderForm;

public interface ReaderService {
	public List<MReader> getAll(ReaderForm form);
	
	public MReader getReader(Integer id);
	
	public void updateReader(MReader reader);
	
	public Reader findReader(Integer id);
	
	public int deleteReader(Integer id);
	
	public void addReader(MReader reader);

	public void updatePasswordReader(MReader map);

}
