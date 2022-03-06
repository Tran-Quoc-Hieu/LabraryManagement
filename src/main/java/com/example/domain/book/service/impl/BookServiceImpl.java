package com.example.domain.book.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import com.example.domain.book.model.Book;
import com.example.domain.book.model.MBook;
import com.example.domain.book.service.BookService;
import com.example.domain.br.model.BrKey;
import com.example.domain.reader.model.Reader;
import com.example.form.BookForm;
import com.example.repository.BookRepository;
import com.example.repository.BrRepository;
import com.example.repository.ReaderRepository;
@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookRepository repository;
	
	@Autowired 
	private ReaderRepository readerRepository;
	
	@Autowired
	private BrRepository brRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public List<MBook> getAll(MBook book) {
		// TODO Auto-generated method stub
		ExampleMatcher matcher = ExampleMatcher
				.matching()
				.withStringMatcher(StringMatcher.CONTAINING)
				.withIgnoreCase();
		return repository.findAll(Example.of(book, matcher));
	}

	@Override
	public MBook getBook(Integer id) {
		// TODO Auto-generated method stub
		MBook book = repository.findById(id).orElse(null);
		for (int i = 0; i < book.getBookBrList().size(); i++) {
			int readerId = book.getBookBrList().get(i).getBrKey().getReaderId();
			Reader readers = mapper.map(readerRepository.findById(readerId).orElse(null), Reader.class);
			book.getBookBrList().get(i).setReader(readers);
		}
		return book;
	}

	@Override
	public void updateBook(MBook book) {
		// TODO Auto-generated method stub
		repository.save(book);
	}

	@Override
	public int deleteBook(Integer id) {
		// TODO Auto-generated method stub
		MBook book = getBook(id);
		List<BrKey> readers = new ArrayList<BrKey>();
		BrKey key = new BrKey();
		for (int i = 0; i < book.getBookBrList().size(); i++) {
			if (book.getBookBrList().get(i).getBrDateReturn() == null) {
				return 1;
			}
			key.setBookId(book.getBookBrList().get(i).getBrKey().getReaderId());
			key.setReaderId(id);
			readers.add(key);
		}
		brRepository.deleteAllByIdInBatch(readers);
		repository.deleteById(id);
		return 0;
	}

	@Override
	public void addBook(MBook book) {
		// TODO Auto-generated method stub
		repository.save(book);
	}

	@Override
	public Book findBook(Integer id) {
		// TODO Auto-generated method stub
		return mapper.map(repository.findById(id).orElse(null), Book.class);
	}

	@Override
	public List<BookForm> getBookForm() {
		List<MBook> listBook = getAll(null);
		List<BookForm> listForm = new ArrayList<BookForm>();
		for (MBook mBook : listBook) {
			listForm.add(mapper.map(mBook, BookForm.class));
		}
		return listForm;
	}

}
