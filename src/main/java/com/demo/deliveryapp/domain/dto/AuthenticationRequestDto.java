package com.demo.deliveryapp.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author jhkim
 * @since 2023-02-09
 *
 */
@NoArgsConstructor
@Getter
public class AuthenticationRequestDto {
	private String email;
	private String password;

	public AuthenticationRequestDto(String email, String password) {
		this.email = email;
		this.password = password;
	}
}
