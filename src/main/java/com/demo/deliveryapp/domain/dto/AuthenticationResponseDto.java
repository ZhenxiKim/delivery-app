package com.demo.deliveryapp.domain.dto;

import lombok.Getter;

/**
 * @author jhkim
 * @since 2023-02-09
 *
 */
@Getter
public class AuthenticationResponseDto {
	private String token;

	public AuthenticationResponseDto(String token) {
		this.token = token;
	}
}
