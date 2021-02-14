package com.book.handler;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.book.entity.PasswordHistory;
import com.book.repository.PasswordHistoryRepository;
import com.book.security.UserDetailsImpl;

@Component
public class PasswordHandler implements HandlerInterceptor {

	@Autowired
	private PasswordHistoryRepository passwordHistoryRepository;

	@Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws IOException {
	 Authentication authentication = (Authentication) request
                .getUserPrincipal();
	 if (authentication != null) {
		 Object principal = authentication.getPrincipal();
		 if (principal instanceof UserDetails) {
			 UserDetailsImpl user = (UserDetailsImpl) principal;
			 PasswordHistory pwHistory = passwordHistoryRepository.findByAccount(user.getAccount());
				if (pwHistory != null) {
					if (LocalDateTime.now().isAfter(pwHistory.getUpdateDate().plusDays(30))) {
						response.sendRedirect(request.getContextPath()
		                         + "/forceEditPassword");
						 return false;
					}
				}
		 }
	 }
	 return true;
 }
}
