package io.kello.ajwt.controller;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.kello.ajwt.domain.account.Account;
import io.kello.ajwt.domain.dto.AccessTestResponseDTO;
import io.kello.ajwt.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@Api(tags = "Test")
public class AccessControlTestController {
	ModelMapper modelMapper = new ModelMapper();
	
	@Autowired
	private AccountService userService;

	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@RequestMapping(method = RequestMethod.GET, value = "/test")
	public String jwtTest() {
		return "success";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER)")
	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@RequestMapping(method = RequestMethod.GET, value = "/whoami")
	public AccessTestResponseDTO whoami(HttpServletRequest req) {
		log.info("AccessControlTest Controller - whoami");
		
		Account account =  userService.whoami(req);
		return new AccessTestResponseDTO(account.getUsername(), account.getEmail(), account.getRoles());

	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER)")
	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@RequestMapping(method = RequestMethod.GET, value = "/refresh")
	public String refresh(HttpServletRequest req) {
		return userService.refresh(req.getRemoteUser());
	}
}
