package com.book.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
 * @author 岡田
 * エンティティクラス
 * book_managementテーブルからデータを取得
 * */
@Entity
@Table(name="book_management")
public class BookEntity {

	// ID
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getGenre() {
		return genre;
	}
	public void setGenre(int genre) {
		this.genre = genre;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getLending() {
		return lending;
	}
	public void setLending(int lending) {
		this.lending = lending;
	}
}