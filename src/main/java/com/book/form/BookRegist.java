package com.book.form;

/**
 * @author 岡田 
 * 編集用クラス
 */
public class BookRegist {

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

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
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
