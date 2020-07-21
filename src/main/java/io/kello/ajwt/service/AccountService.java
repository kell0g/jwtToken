package io.kello.ajwt.service;

import io.kello.ajwt.domain.account.Account;

public interface AccountService {
	
	// Sign up
	void createAccount(Account account);
	
	// Sign in with JWT
	String signin(String username, String password);
	
	//Employee whoami(HttpServletRequest req);
	
}
