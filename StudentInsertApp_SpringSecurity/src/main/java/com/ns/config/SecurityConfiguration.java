package com.ns.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ns.repository.StudentRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final StudentRepository studentRepository;

	public SecurityConfiguration(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.StudentService(id -> studentRepository.findByStudentId(id)
				.orElseThrow(() -> new UsernameNotFoundException(format("User: %s, not found", id))));
	}
}
