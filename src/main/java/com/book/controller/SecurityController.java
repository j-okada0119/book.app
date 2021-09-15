package com.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.book.entity.Account;
import com.book.form.AccountForm;
import com.book.repository.AccountRepository;
import com.book.security.UserDetailsServiceImpl;

@Controller
public class SecurityController {
	
	/**
	 * アカウントリポジトリクラス
	 */
	@Autowired
	private AccountRepository repository;
	
	// リポジトリ登録するクラス
	@Autowired
	private UserDetailsServiceImpl userDetails;
	
	/**
	 * ログインユーザ一覧画面を表示
	 * @param　model
	 * 
	 * @return　一覧画面
	 */
	@RequestMapping("/security/userList")
	public String userList(Model model) {
		
		// DBのデータ全件取得
		List<Account> accountList = repository.findAll();
		// 権限を日本語に変換
		accountList.stream().forEach(user -> convertRole(user));
		model.addAttribute("accountList", accountList);
		
		return "/security/userList";
	}

	/**
	 * ログインユーザ一の権限を日本語に変換
	 * @param　account
	 * 		変換するアカウント
	 */
	private void convertRole(Account account) {
		// 権限
		String role = account.getRole();
		
		if ("ADMIN".equals(role)) {
			account.setRole("管理者");
		} else {
			account.setRole("一般");
		}
	}
	
	/**
	 * ログインユーザ登録画面を表示
	 * @param　model
	 * 
	 * @return　登録画面
	 */
	@RequestMapping(value = "/security/add", method = RequestMethod.GET)
	public String add(Model model) {
		
		model.addAttribute("accountForm", new AccountForm());
		
		return "/security/add";
	}
	
	/**
	 * ログインユーザ新規登録
	 * 
	 * @param　model
	 * 
	 * @return　
	 */
	@RequestMapping(value="/security/add", params="add", method=RequestMethod.POST)
	public String add(Model model, @Validated AccountForm account, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("accountForm", account);
			return "/security/add";
		}
		if (repository.existsByUsername(account.getUsername())) {
			FieldError fieldError = new FieldError(result.getObjectName(), "username",
					"入力されたユーザ名は既に存在しています。別のユーザ名を入力してください。");
			result.addError(fieldError);
			model.addAttribute("accountForm", account);
			return "/security/add";
		}
		
		
		// 権限
		String role = account.getRole();
		
		if ("ADMIN".equals(role)) {
			// 管理者権限で登録
			userDetails.registerAdmin(account.getUsername(), account.getPassword());
		} else {
			// 一般ユーザ権限で登録
			userDetails.registerUser(account.getUsername(), account.getPassword());
		}
		
		return "redirect:/security/userList";
	}
	
	/**
	 * 登録画面から一覧画面へ戻る
	 * 
	 * @param　model
	 * 
	 * @return　一覧画面
	 */
	@RequestMapping(value="/security/add", params="back", method=RequestMethod.POST)
	public String backFromAdd() {
		return "redirect:/security/userList";
	}
	
	/**
	 * 編集画面表示
	 * 
	 * @param　model
	 * 
	 * @return　
	 */
	@RequestMapping(value="/security/edit{id}", method=RequestMethod.GET)
	public String edit(@PathVariable int id, Model model) {
		Account account = repository.findById(id).get();
		
		model.addAttribute("account", account);
		
		return "/security/edit";
	}
	
	/**
	 * 編集した内容を登録
	 * 
	 * @param　model
	 * 
	 * @return　一覧画面
	 */
	@RequestMapping(value="/security/edit", params="edit", method=RequestMethod.POST)
	public String edit(@ModelAttribute Account account) {
		
		userDetails.editUser(account);
		
		return "redirect:/security/userList";
	}
	
	/**
	 * 編集画面から一覧画面へ戻る
	 * 
	 * @param　model
	 * 
	 * @return　一覧画面
	 */
	@RequestMapping(value="/security/edit", params="back", method=RequestMethod.POST)
	public String backFromEdit() {
		return "redirect:/security/userList";
	}
	
	/**
	 * 削除処理
	 * 
	 * @param　model
	 * 
	 * @return　一覧画面
	 */
	@RequestMapping(value="/security/delete{id}", method=RequestMethod.POST)
    public String delete(@PathVariable int id) {
		userDetails.deleteUser(id);
        return "redirect:/security/userList";
    }
}
