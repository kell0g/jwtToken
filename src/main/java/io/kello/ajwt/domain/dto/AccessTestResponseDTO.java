package io.kello.ajwt.domain.dto;

import java.util.List;

import io.kello.ajwt.domain.account.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccessTestResponseDTO {
	String username;
	String email;
	List<Role> roles;
}