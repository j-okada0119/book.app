package com.book.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;

import com.book.security.AccountService;

import lombok.Data;

/** 
 * @author 岡田
 * アカウントクラス
 * */
@Entity
@Table(name="ACCOUNT")
@Data
public class Account {
	
	// ID
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	// ユーザ名
	@Column(name = "username")
	@NotBlank(message = "ユーザ名を入力してください")
	private String username;
	
	// パスワード
	@Column(name = "password")
	@NotBlank(message = "パスワードを入力してください")
	private String password;
	
	// 権限
	@Column(name = "role")
	private String role;
	
	public Account(){}
	
	public Account(String username, String password, String role) {
		this.id=0;
		this.username = username;
		this.password = password;
		this.role = role;
	}
}
