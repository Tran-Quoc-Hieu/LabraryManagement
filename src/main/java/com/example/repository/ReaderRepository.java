package com.example.repository;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.domain.reader.model.MReader;
@Transactional
public interface ReaderRepository extends JpaRepository<MReader, Integer>{
	@Modifying
	@Query("update MReader set readerEmail =:readerEmail, readerName =:readerName, readerAddress =:readerAddress"
			+ " where readerId =:readerId")
	public void updateReader(@Param("readerId") Integer readerId, 
			@Param("readerEmail") String readerEmail, 
			@Param("readerName") String readerName, 
			@Param("readerAddress") String readerAddress);
	
	@Modifying
	@Query("update MReader set readerPassword =:readerPassword"
			+ " where readerId =:readerId")
	public void updatePasswordReader(@Param("readerId") Integer readerId, 
			@Param("readerPassword") String readerPassword);
	
	
}
