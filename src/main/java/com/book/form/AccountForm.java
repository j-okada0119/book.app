package com.book.form;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class AccountForm {
	
	// ユーザ名
	@NotBlank(message = "ユーザ名を入力してください")
	private String username;
	
	// パスワード
	@NotBlank(message = "パスワードを入力してください")
	private String password;
	
	// 権限
	private String role;
}
