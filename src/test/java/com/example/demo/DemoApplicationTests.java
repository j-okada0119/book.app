package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.book.security.UserDetailsServiceImpl;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private UserDetailsServiceImpl sv;
	
	@Test
	void contextLoads() {
	}

}
