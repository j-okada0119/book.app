package com.book.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/** 
 * @author 岡田
 * アカウントクラス
 * */
@Entity
@Table(name="ACCOUNT")
@Data
public class Account {
	
	public Account(){}
	
	public Account(String username, String password, String role) {

		setId(0);
		setUsername(username);
		setPassword(password);
		setRole(role);

	}
	
		// ID
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name = "id")
		private int id;
		
		// ユーザ名
		@Column(name = "username")
		private String username;
		
		// パスワード
		@Column(name = "password")
		private String password;
		
		// 権限
		@Column(name = "role")
		private String role;
}
