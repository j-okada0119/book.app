package com.book.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.book.entity.BookEntity;
import com.book.form.BookEditForm;
import com.book.form.BookRegist;

@Service
public class ConvertUtil {
	
	/**
	 * 画面表示用のリストに変換するメソッド
	 * 
	 * @param book リポジトリから取得したリスト
	 * 
	 * @return 画面表示用リスト
	 */
	public List<BookEditForm> convertList(List<BookEntity> book) {

		List<BookEditForm> convertBookList = new ArrayList<>();
		// 画面表示用のリストに詰め替え
		for (BookEntity list : book) {

			BookEditForm bookList = new BookEditForm();
			// ID
			bookList.setId(list.getId());
			// タイトル
			bookList.setTitle(list.getTitle());
			
			// ジャンル
			String genre = null;
			switch (list.getGenre()) {
			
			case 0:
				genre = "Java";
				break;
			case 1:
				genre = "PHP";
				break;
			case 2:
				genre = "C言語";
				break;
			}
			bookList.setGenre(genre);
			
			// 内容
			bookList.setDetails(list.getDetails());
			// 著者
			bookList.setAuthor(list.getAuthor());
			// 貸出有無
			bookList.setLending(list.getLending() == 0 ? "陳列中" : "貸出中");

			convertBookList.add(bookList);
		}
		return convertBookList;
	}

	/**
	 * 登録用のリストに変換するメソッド
	 * 
	 * @param book 画面取得したリスト
	 * 
	 * @return 登録用リスト
	 */
	public BookEntity convertRegist(BookRegist createBook) {

		    // 登録用

			BookEntity bookEntity = new BookEntity();
			// ID
			bookEntity.setId(createBook.getId());
			// タイトル
			bookEntity.setTitle(createBook.getTitle());
			
			// ジャンル
			int genre = 0;
			switch (createBook.getGenre()) {
			
			case "Java":
				break;
			case "PHP":
				genre = 1;
				break;
			case "C言語":
				genre = 2;
				break;
			}
			bookEntity.setGenre(genre);
			
			// 内容
			bookEntity.setDetails(createBook.getDetails());
			// 著者
			bookEntity.setAuthor(createBook.getAuthor());
			// 貸出有無
			bookEntity.setLending(createBook.getLending());

		return bookEntity;
	}
}
