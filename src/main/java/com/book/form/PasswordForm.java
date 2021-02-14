package com.book.form;

import lombok.Data;

@Data
public class PasswordForm {

	private String oldPassword;

	private String newPassword;
	
	private String secondNewPassword;
}
