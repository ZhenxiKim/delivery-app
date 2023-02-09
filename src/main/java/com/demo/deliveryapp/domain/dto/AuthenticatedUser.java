package com.demo.deliveryapp.domain.dto;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author jhkim
 * @since 2023-02-09
 *
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AuthenticatedUser implements UserDetails {

	private Long memberNo;

	private String memberEmail;//회원 이메일

	private String memberPassword;//회원 비밀번호

	private String memberName;//회원이름

	private LocalDateTime memberSignupDate;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}


}
