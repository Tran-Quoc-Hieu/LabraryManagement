package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.book.model.MBook;
@Repository
public interface BookRepository extends JpaRepository<MBook, Integer>{

}
