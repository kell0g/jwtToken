package io.kello.ajwt.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.kello.ajwt.domain.account.Account;
import io.kello.ajwt.domain.account.Role;
import io.kello.ajwt.domain.dto.AccountDTO;
import io.kello.ajwt.domain.dto.SignUpResponseDTO;
import io.kello.ajwt.service.AccountService;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "SignUp")
public class SignUpController {
	// Repository Service
	@Autowired
	private AccountService userService;

	// For Password Encryption
	@Autowired
	private PasswordEncoder passwordEncoder;

	@RequestMapping(method = RequestMethod.POST, value = "/signup")
	public SignUpResponseDTO signUp(@RequestBody AccountDTO accountDTO) {
		ModelMapper modelMapper = new ModelMapper();
		Account account = modelMapper.map(accountDTO, Account.class);

		// Grant a user roles by default.
		List<Role> roles = new ArrayList<>(Arrays.asList(Role.ROLE_USER));
		account.setRoles(roles);

		// PasswordEncoder uses bcrypt hash algorithm by default.
		account.setPassword(passwordEncoder.encode(account.getPassword()));

		try {
			userService.createAccount(account);
		} catch (Exception e) {
			e.printStackTrace();
			return new SignUpResponseDTO("Failure", e.getMessage());
		}
		return new SignUpResponseDTO("Success", "Successful sign up");
	}
}