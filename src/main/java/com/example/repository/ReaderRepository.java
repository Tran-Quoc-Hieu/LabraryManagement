package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.reader.model.MReader;

@Repository
public interface ReaderRepository extends JpaRepository<MReader, Integer>{

}
