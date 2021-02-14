package com.book.security;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.HandlerInterceptor;

import com.book.entity.Account;
import com.book.entity.PasswordHistory;
import com.book.repository.AccountRepository;
import com.book.repository.PasswordHistoryRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, HandlerInterceptor {

	@Autowired
    private AccountRepository accountRepository;
	
	@Autowired
	private PasswordHistoryRepository passwordHistoryRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
	
    @Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	       if (username == null || "".equals(username)) {
	            throw new UsernameNotFoundException("Username is empty");
	        }

	        Account ac = accountRepository.findByUsername(username);
	        if (ac == null) {
	            throw new UsernameNotFoundException("User not found: " + username);
	        }
	        
	        UserDetailsImpl user = new UserDetailsImpl(ac,getAuthorities(ac));

	        return user;
	}
	
	private Collection<GrantedAuthority> getAuthorities(Account account){
		
		if("ADMIN".equals(account.getRole())){
			return AuthorityUtils.createAuthorityList("ROLE_ADMIN","ROLE_USER");
		}else{
			return AuthorityUtils.createAuthorityList("ROLE_USER");
		}
		
	}

    @Transactional
    public void registerAdmin(String username, String password) {
    	PasswordHistory pwHistory = new PasswordHistory();
        Account user = new Account(username, passwordEncoder.encode(password),"ADMIN");
        pwHistory.setAccount(user);
        pwHistory.setUpdateDate(LocalDateTime.now());
        passwordHistoryRepository.save(pwHistory);
    }

    @Transactional
    public void registerUser(String username, String password) {
    	PasswordHistory pwHistory = new PasswordHistory();
    	Account user = new Account(username, passwordEncoder.encode(password),"USER");
        pwHistory.setAccount(user);
        pwHistory.setUpdateDate(LocalDateTime.now());
        passwordHistoryRepository.save(pwHistory);
    }
    
    @Transactional
    public void editUser(Account account) {
    	Account editAccount = accountRepository.findById(account.getId()).get();
    	
    	editAccount.setUsername(account.getUsername());
    	
    	if ("ADMIN".equals(account.getRole())) {
    		editAccount.setRole("ADMIN");
    	} else {
    		editAccount.setRole("USER");
    	}
    		      
        accountRepository.save(editAccount);
    }
    
    @Transactional
    public void deleteUser(int id) {
    	Account account = accountRepository.findById(id).get();
    	passwordHistoryRepository.deleteByAccount(account);
    }
}
