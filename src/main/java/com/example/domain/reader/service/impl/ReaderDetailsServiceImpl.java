package com.example.domain.reader.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.domain.reader.model.MReader;
import com.example.domain.reader.service.ReaderService;

@Service
public class ReaderDetailsServiceImpl implements UserDetailsService{
	@Autowired
	@Lazy
	private ReaderService service;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		// Lấy thông tin người dùng
		MReader reader = service.getLogin(userName);
		//	Nếu không tìm thấy -> thoát 
		if (reader == null) {
			throw new UsernameNotFoundException("Email not found");
		}
		// Tạo danh sách quyền
		GrantedAuthority authority = new SimpleGrantedAuthority(reader.getRole());
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(authority);
		// Tạo chi tiết người dùng
		UserDetails userDetails = (UserDetails) new User(reader.getReaderEmail(), reader.getReaderPassword(), authorities);
		return userDetails;
	}
	
	
}
