package com.example.domain.pc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.domain.pc.model.MPub;
import com.example.domain.pc.service.PcService;
import com.example.repository.PcRepository;

@Service
@Primary
public class PcServiceImpl implements PcService{
	
	@Autowired
	private PcRepository repository;

	@Override
	public List<MPub> findMany() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public MPub findOne(Integer pcId) {
		// TODO Auto-generated method stub
		return repository.findById(pcId).orElse(null);
	}

	@Override
	public void deletePc(Integer pcId) {
		// TODO Auto-generated method stub
		repository.deleteById(pcId);
	}

	@Override
	public void update(Integer pcId) {
		// TODO Auto-generated method stub
		
	}

}
