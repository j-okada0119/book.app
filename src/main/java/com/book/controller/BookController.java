package com.book.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.book.entity.BookEntity;
import com.book.form.BookEditForm;
import com.book.form.BookRegist;
import com.book.repository.BookRepository;
import com.book.util.ConvertUtil;

/**
 * コントローラークラス
 * 
 * @author 岡田 
 */
@Controller
@EnableJpaRepositories("com.book.repository")
public class BookController {

	/**
	 * 書籍リポジトリクラス
	 */
	@Autowired
	private BookRepository repository;

	// 変換・作成クラス
	@Autowired
	private ConvertUtil convertUtil;
	
	/**
	 * リポジトリからDBの値を取得し画面に表示させる
	 * 
	 * @param　model
	 * 
	 * @return　index.html
	 */
	@RequestMapping("/index")
	public String index(Model model) {
		
		// DBのデータ全件取得
		List<BookEntity> book = repository.findAll();
		// 画面表示用にリストを変換
		List<BookEditForm> bookList = convertUtil.convertList(book);
		model.addAttribute("bookList", bookList);
		return "/index";
	}
	
	/**
	 * 新規登録画面を表示
	 * @param　model
	 * 
	 * @return　登録画面
	 */
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String regist(Model model) {
		
		// ラジオボタンの選択肢
        Map<Integer, String> radioMap = new LinkedHashMap<Integer, String>();
        radioMap.put(0, "陳列中");
        radioMap.put(1, "貸出中");

        //　全件取得
        List<BookEntity> book = repository.findAll();
        // 最後に登録したIDを取得
        Optional<Integer> lastId = book.stream().map(BookEntity::getId).max((id1, id2) -> id1 - id2);       
        model.addAttribute("id", lastId.orElse(0) + 1);
        model.addAttribute("items", radioMap);
		model.addAttribute("bookRegist", new BookRegist());
		return "/registration";
	}
	
	/**
	 * 画面からリポジトリへの登録
	 * 
	 * @param　model
	 * 
	 * @return　index.html
	 */
	@RequestMapping(value="/create", params="add", method=RequestMethod.POST)
	public String create(@ModelAttribute BookRegist bookRegist) {
		
		BookEntity entity = convertUtil.convertRegist(bookRegist);
		
		repository.save(entity);
		
		return "redirect:/index";
	}
	
	/**
	 * 登録画面から一覧画面へ戻る
	 * 
	 * @param　model
	 * 
	 * @return　index.html
	 */
	@RequestMapping(value="/create", params="back", method=RequestMethod.POST)
	public String create() {	
		return "redirect:/index";
	}
	
}