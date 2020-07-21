package io.kello.ajwt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.kello.ajwt.domain.account.Account;
import io.kello.ajwt.repository.AccountRepository;
import io.kello.ajwt.service.impl.AccountContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private AccountRepository userRepository;

	// username => email
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account user = userRepository.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("username doesn't exist");
		}

		List<GrantedAuthority> roles = new ArrayList<>();
		user.getRoles().forEach(role -> roles.add(role));

		AccountContext accountContext = new AccountContext(user, roles);


		return accountContext;
	}
}
