package com.book.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.book.entity.BookEntity;

/** 
 * @author 岡田
 * 書籍用リポジトリクラス
 * */
@Repository
public interface BookRepository extends JpaRepository <BookEntity, Integer> {
	}
