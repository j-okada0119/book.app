package com.book.form;

import org.springframework.beans.BeanUtils;

import com.book.entity.BookEntity;

import lombok.Data;

/**
 * @author 岡田 
 * 編集用クラス
 */
@Data
public class BookRegist {

	// ID
	private int id;
	// タイトル
	private String title;
	// ジャンル
	private int genre;
	// 内容
	private String details;
	// 著者
	private String author;
	// 貸出
	private int lending;

	public BookEntity toBookEntity() {
		BookEntity entity = new BookEntity();
		BeanUtils.copyProperties(this, entity);
		return entity;
	}
}
