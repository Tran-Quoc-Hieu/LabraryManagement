package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.pc.model.MPub;

@Repository
public interface PcRepository extends JpaRepository<MPub, Integer>{

}
