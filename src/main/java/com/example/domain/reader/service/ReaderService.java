package com.example.domain.reader.service;

import java.util.List;

import com.example.domain.reader.model.MReader;

public interface ReaderService {
	public List<MReader> getAll();
	
	public MReader getReader(Integer id);
	
	public void updateReader();
	
	public void deleteReader(Integer id);
	
	public void addReader(MReader reader);
}
