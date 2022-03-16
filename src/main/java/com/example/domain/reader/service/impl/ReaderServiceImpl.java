package com.example.domain.reader.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.domain.book.model.Book;
import com.example.domain.br.model.BrKey;
import com.example.domain.reader.model.MReader;
import com.example.domain.reader.model.Reader;
import com.example.domain.reader.service.ReaderService;
import com.example.repository.BookRepository;
import com.example.repository.BrRepository;
import com.example.repository.ReaderRepository;

@Service
public class ReaderServiceImpl implements ReaderService{
	@Autowired
	private ReaderRepository repository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BrRepository brRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public List<MReader> getAll(MReader form) {
		// TODO Auto-generated method stub
		ExampleMatcher matcher = ExampleMatcher
				.matching()
				.withStringMatcher(StringMatcher.CONTAINING)
				.withIgnoreCase();
		return repository.findAll(Example.of(form, matcher));
	}

	@Override
	public MReader getReader(Integer id) {
		// TODO Auto-generated method stub
		MReader reader = repository.findById(id).orElse(null);
		for (int i = 0; i < reader.getReaderBrList().size(); i++) {
			int bookId = reader.getReaderBrList().get(i).getBrKey().getBookId();
			Book book = mapper.map(bookRepository.findById(bookId).orElse(null), Book.class);
			reader.getReaderBrList().get(i).setBook(book);
		}
		return reader;
	}

	@Override
	public void updateReader(MReader reader) {
		// TODO Auto-generated method stub
		repository.updateReader(reader.getReaderId(), reader.getReaderEmail(), reader.getReaderName(), reader.getReaderAddress());
	}
	

	@Override
	public void updatePasswordReader(MReader map) {
		// TODO Auto-generated method stub
		map.setReaderPassword(encoder.encode(map.getReaderPassword()));
		repository.updatePasswordReader(map.getReaderId(), map.getReaderPassword());
	}

	@Override
	public int deleteReader(Integer id) {
		// TODO Auto-generated method stub
		MReader reader = getReader(id);
		List<BrKey> book = new ArrayList<BrKey>();
		BrKey key = new BrKey();
		for (int i = 0; i < reader.getReaderBrList().size(); i++) {
			if (reader.getReaderBrList().get(i).getBrDateReturn() == null) {
				return 1;
			}
			key.setBookId(reader.getReaderBrList().get(i).getBrKey().getBookId());
			key.setReaderId(id);
			book.add(key);
		}
		brRepository.deleteAllByIdInBatch(book);
		repository.deleteById(id);
		return 0;
	}

	@Override
	public void addReader(MReader reader) {
		// TODO Auto-generated method stub
		reader.setRole("ROLE_GENERAL");
		reader.setReaderPassword(encoder.encode(reader.getReaderPassword()));
		repository.save(reader);
	}
	
	@Override
	public Reader findReader(Integer id) {
		// TODO Auto-generated method stub
		return mapper.map(repository.findById(id).orElse(null), Reader.class);
	}

	@Override
	public MReader getLogin(String email) {
		// TODO Auto-generated method stub
		return repository.findByReaderEmail(email); //repository.getLogin(email);
	}

	@Override
	public void addUser(MReader user) {
		// TODO Auto-generated method stub
		MReader reader = repository.findByReaderEmail(user.getReaderEmail());
		if (reader != null) {
			throw new DataAccessException("User alraeady exits") {};
		}
		user.setRole("ROLE_GENERAL");
		user.setReaderPassword(encoder.encode(user.getReaderPassword()));
		repository.save(user);
	}

}
