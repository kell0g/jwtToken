package io.kello.ajwt.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class JwtDTO {
	@ApiModelProperty(position = 0)
	private String username;
	@ApiModelProperty(position = 1)
	private String password;
}
