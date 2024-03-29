package com.book.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.book.entity.Account;


@SuppressWarnings("serial")
public class UserDetailsImpl implements UserDetails {
	
	// アカウント
	private Account user;
	// 権限リスト
	private Collection<GrantedAuthority> authorities;
	
	public UserDetailsImpl(Account account, Collection<GrantedAuthority> authorities){
		this.user = account;
		this.authorities = authorities;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Account getAccount() {
		return this.user;
	}

}
