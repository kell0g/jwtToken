package io.kello.ajwt.service.impl;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import io.kello.ajwt.domain.account.Account;
import lombok.Data;

@Data
public class AccountContext extends User {
	private static final long serialVersionUID = 1L;
	private Account user;

	public AccountContext(Account user, List<GrantedAuthority> roles) {
		// super(account.getUsername(), account.getPassword(), roles);
		super(user.getUsername(), user.getPassword(), roles);
		this.user = user;
	}
}
