package io.kello.ajwt.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.kello.ajwt.domain.account.Account;
import io.kello.ajwt.exception.CustomException;
import io.kello.ajwt.repository.AccountRepository;
import io.kello.ajwt.security.provider.JwtTokenProvider;
import io.kello.ajwt.service.AccountService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("userService")
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository userRepository;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private AuthenticationManager authenticationManager;

	
	// Sign up
	@Transactional
	@Override
	public void createAccount(Account account) {
		userRepository.save(account);
	}

	
	// Sign in with JWT
	public String signin(String username, String password) {
		try {
			log.info(password);
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
		} catch (AuthenticationException e) {
			throw new CustomException("Invalid username, password", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	// Authorization using JWT
	public Account whoami(HttpServletRequest req) {
		return userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
	}
}
