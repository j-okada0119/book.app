package com.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/** 
 * @author 岡田
 * アプリケーション実行クラス
 * */
@SpringBootApplication
@EntityScan("com.book.entity")
@EnableJpaRepositories("com.book.repository.repository")
public class BookApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookApplication.class, args);
	}

}
