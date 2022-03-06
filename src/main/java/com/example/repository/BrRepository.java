package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.br.model.BrKey;
import com.example.domain.br.model.MBr;

@Repository
public interface BrRepository extends JpaRepository<MBr, BrKey>{
}
