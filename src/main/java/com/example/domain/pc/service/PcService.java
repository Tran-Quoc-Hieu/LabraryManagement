package com.example.domain.pc.service;

import java.util.List;

import com.example.domain.pc.model.MPub;

public interface PcService {
	public List<MPub> findMany();
	
	public MPub findOne(Integer pcId);
	
	public void deletePc(Integer pcId);
	
	public void update(Integer pcId);
}
