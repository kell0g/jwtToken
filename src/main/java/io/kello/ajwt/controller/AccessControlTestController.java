package io.kello.ajwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@Api(tags = "Test")
public class AccessControlTestController {

	//ModelMapper modelMapper = new ModelMapper();

//	@Autowired
//	private AccountService userService;

	// password Encryption
	@Autowired

	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@RequestMapping(method = RequestMethod.GET, value = "/test")
	public String jwtTest() {
		return "success";
	}

//	@GetMapping(value = "/me")
//	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
//	
}
