package com.book.form;

import org.springframework.beans.BeanUtils;

import com.book.common.BookGenre;
import com.book.entity.BookEntity;

import lombok.Data;

/**
 * @author 岡田 
 * 編集用クラス
 */
@Data
public class BookEditForm {

	// ID
	private int id;

	// タイトル
	private String title;

	// ジャンル
	private String genre;

	// 内容
	private String details;

	// 著者
	private String author;

	// 貸出
	private String lending;

	public BookEditForm() {
	}

	public BookEditForm(BookEntity entity) {
		BeanUtils.copyProperties(entity, this);
		this.genre = BookGenre.findByGenreId(entity.getGenre()).getGenreNm();
	}
}
