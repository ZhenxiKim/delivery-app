package com.demo.deliveryapp.security;

import java.util.Base64;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

/**
 * @author jhkim
 * @since 2023-02-08
 *
 */
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
	private String secretKey = "delivery-app";
	private final UserDetailsService userDetailsService;

	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	/**
	 * JWT 토큰 생성
	 * @param userPk
	 * @return
	 */
	public String createToken(String userPk) {
		long tokenValidTime = 30 * 60 * 1000L;// 토큰 유효시간 1분
		Claims claims = Jwts.claims().setSubject(userPk); // JWT payload 에 저장되는 정보단위, 보통 여기서 user를 식별하는 값을 넣는다.
		Date now = new Date();
		return Jwts.builder()
			.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
			.setClaims(claims)
			.setIssuedAt(now)
			.setExpiration(new Date(now.getTime() + tokenValidTime))
			.signWith(SignatureAlgorithm.HS256, secretKey)
			.compact();
	}

	/**
	 * JWT 토큰에서 인증 정보 조회
	 * @param token
	 * @return
	 */
	public Authentication getAuthentication(String token) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
		return new UsernamePasswordAuthenticationToken(userDetails, "");
	}

	/**
	 * 토큰에서 회원 정보 추출
	 * @param token
	 * @return
	 */
	public String getUserPk(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	}

	/**
	 * Request의 Header에서 token 값을 가져옵니다. "Authorization" : "TOKEN값'
	 * @param request
	 * @return
	 */
	public String resolveToken(HttpServletRequest request) {
		return request.getHeader("Authorization");
	}

	/**
	 * 토큰의 유효성 + 만료일자 확인
	 * @param jwtToken
	 * @return
	 */
	public boolean validateToken(String jwtToken) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
			return !claims.getBody().getExpiration().before(new Date());
		} catch (Exception e) {
			return false;
		}
	}
}
