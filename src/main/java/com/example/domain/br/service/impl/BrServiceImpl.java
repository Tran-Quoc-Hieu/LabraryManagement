package com.example.domain.br.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.book.model.MBook;
import com.example.domain.book.service.BookService;
import com.example.domain.br.model.BrKey;
import com.example.domain.br.model.MBr;
import com.example.domain.br.service.BrService;
import com.example.domain.reader.model.MReader;
import com.example.domain.reader.service.ReaderService;
import com.example.form.BookForm;
import com.example.form.BorrowReturnForm;
import com.example.form.BrNew;
import com.example.form.BrSearchForm;
import com.example.repository.BrRepository;

@Service
public class BrServiceImpl implements BrService {

	@Autowired
	private BrRepository brRepository;

	@Autowired
	private BookService bookService;

	@Autowired
	private ReaderService readerService;

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<MBr> getMany(BrSearchForm form) {
		// TODO Auto-generated method stub
		List<MBr> getList = brRepository.findAll();
		for (int i = 0; i < getList.size(); i++) {
			getList.set(i, getBr(getList.get(i).getBrKey()));
		}
		if (form.getSearch() == null || form.getSearch().equals("all"))
			return getList;
		List<MBr> listBr = new ArrayList<MBr>();
		for (int i = 0; i < getList.size(); i++) {
			if (form.getSearch().equals("returned") && getList.get(i).getBrDateReturn() != null) {
				listBr.add(getList.get(i));
			} else if (form.getSearch().equals("dontReturn") && getList.get(i).getBrDateReturn() == null) {
				listBr.add(getList.get(i));
			}
		}
		return listBr;
	}

	@Override
	public MBr getBr(BrKey br) {
		// TODO Auto-generated method stub
		MBr brNew = brRepository.findById(br).orElse(null);
		if (brNew != null) {
			brNew.setBook(bookService.findBook(brNew.getBrKey().getBookId()));
			brNew.setReader(readerService.findReader(brNew.getBrKey().getReaderId()));
		}
		return brNew;
	}

	@Override
	public void update(BorrowReturnForm form) {
		// TODO Auto-generated method stub
		Date date = new Date();
		MBr br = brRepository.getById(form.getBrKey());
		br.setBrDateReturn(date);
		brRepository.save(br);
	}

	@Override
	public int delete(BorrowReturnForm form) {
		// TODO Auto-generated method stub
		MBr br = getBr(form.getBrKey());
		if (br.getBrDateReturn() == null) {
			return 1;
		}
		brRepository.deleteById(form.getBrKey());
		return 0;
	}

	@Override
	public void addMBr(BrKey brkey) {
		// TODO Auto-generated method stub
		Date date = new Date();
		MBr br = new MBr();
		br.setBrKey(brkey);
		br.setBrDateBorrow(date);
		brRepository.save(br);
	}
}
