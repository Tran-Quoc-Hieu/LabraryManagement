package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private PasswordEncoder coder;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// Không áp dụng bảo mật
		web
			.ignoring()
			.antMatchers("/webjars/**")
			.antMatchers("/css/**")
			.antMatchers("/js/**")
			.antMatchers("/h2-console/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Tập hợp các trang đăng nhập không cần thiết
		http
			.authorizeRequests()
			.antMatchers("/login").permitAll() // Liên kết trực tiếp
			.antMatchers("/signup").permitAll() // Liên kết trực tiếp
			.antMatchers("/forgot").permitAll()
			.anyRequest().authenticated(); // Nếu không thì liên kết trực tiếp NG
		
//		Login
		http
			.formLogin()
			.loginProcessingUrl("/login") // Đường dẫn quy trình đăng nhập
			.loginPage("/login") // Chỉ định trang đăng nhập
			.failureUrl("/login?error") // đích chuyển đổi khi đăng nhập không thành công
			.usernameParameter("userName") // user đăng nhập
			.passwordParameter("password") // pass đăng nhập
			.defaultSuccessUrl("/home", true); // Điểm đến chuyển tiếp sau khi thành công
//		Logout
		http
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutUrl("/logout")
			.logoutSuccessUrl("/login");
//		// Tắt các biện pháp CSRF (tạm thời)
		http.csrf().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder encoder = coder;
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(encoder);
	}
	
}
