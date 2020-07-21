package io.kello.ajwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.kello.ajwt.domain.dto.JwtDTO;
import io.kello.ajwt.domain.dto.SignInResponseDTO;
import io.kello.ajwt.service.AccountService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@Api(tags = "SignIn")
public class SignInController {
	// Repository Service
	@Autowired
	private AccountService userService;

	@RequestMapping(method = RequestMethod.POST, value = "/signin")
	public SignInResponseDTO signIn(@RequestBody JwtDTO jwtDTO) {
		
		log.info("SignIn Controller");
		
		String token = null;
		try {
		token= userService.signin(jwtDTO.getUsername(), jwtDTO.getPassword());
		}
		catch(Exception e) {
			return new SignInResponseDTO("Failure", e.getMessage());
		}
		return new SignInResponseDTO("Success", token);
	}
}

