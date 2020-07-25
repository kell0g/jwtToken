package io.kello.ajwt.service;

import javax.servlet.http.HttpServletRequest;

import io.kello.ajwt.domain.account.Account;

public interface AccountService {
	
	// Sign up
	void createAccount(Account account);
	
	// Sign in with JWT
	String signin(String username, String password);
	
	Account whoami(HttpServletRequest req);
	
	String refresh(String username);
	
}
