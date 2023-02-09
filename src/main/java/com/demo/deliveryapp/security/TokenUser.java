package com.demo.deliveryapp.security;

import io.jsonwebtoken.Claims;
import lombok.Getter;
import lombok.Setter;

/**
 * @author jhkim
 * @since 2023-02-08
 *
 */
@Getter
@Setter
public class TokenUser {
	private String userEmail;

	public TokenUser(Claims claims) {
		this.userEmail = String.valueOf(claims.get("sub"));
	}

}
