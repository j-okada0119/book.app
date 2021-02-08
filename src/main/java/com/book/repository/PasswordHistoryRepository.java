package com.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.book.entity.Account;
import com.book.entity.PasswordHistory;

/** 
 * パスワード履歴リポジトリクラス.
 * 
 * @author 岡田
 * */
@Repository
public interface PasswordHistoryRepository extends JpaRepository<PasswordHistory, Integer> {
	void deleteByAccount(Account account);
}
