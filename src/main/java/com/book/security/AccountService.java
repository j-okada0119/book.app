package com.book.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.entity.Account;
import com.book.repository.AccountRepository;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;

	public Optional<Account> findById(int id) {
		return accountRepository.findById(id);
	}

	public Account findByUsername(String username) {
		return accountRepository.findByUsername(username);
	}

	public void save(Account account) {
		accountRepository.save(account);
	}

	public boolean existsByUserName(String username) {
		return accountRepository.existsByUsername(username);
	}
}
