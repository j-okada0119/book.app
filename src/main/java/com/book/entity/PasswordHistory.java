package com.book.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * パスワード履歴エンティティクラス.
 * 
 * @author 岡田
 */
@Entity
@Table(name="PASSWORD_HISTORY")
@Data
public class PasswordHistory {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "update_date")
	private LocalDateTime updateDate;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_Id")
    private Account account;
}
