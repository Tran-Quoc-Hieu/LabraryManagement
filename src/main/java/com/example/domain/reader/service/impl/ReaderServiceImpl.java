package com.example.domain.reader.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.reader.model.MReader;
import com.example.domain.reader.service.ReaderService;
import com.example.repository.ReaderRepository;

@Service
public class ReaderServiceImpl implements ReaderService{
	@Autowired
	private ReaderRepository repository;

	@Override
	public List<MReader> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public MReader getReader(Integer id) {
		// TODO Auto-generated method stub
		return repository.findById(id).orElse(null);
	}

	@Override
	public void updateReader() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteReader(Integer id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	@Override
	public void addReader(MReader reader) {
		// TODO Auto-generated method stub
		repository.save(reader);
	}

}
