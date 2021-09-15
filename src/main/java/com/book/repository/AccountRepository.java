package com.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.book.entity.Account;

/** 
 * @author 岡田
 * アカウントリポジトリクラス
 * */
@Repository
public interface AccountRepository extends JpaRepository <Account, Integer> {
	
	public Account findByUsername(String username);

	public boolean existsByUsername(String username);
}
