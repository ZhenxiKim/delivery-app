package com.demo.deliveryapp.service.impl;

import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.deliveryapp.domain.dto.AuthenticationResponseDto;
import com.demo.deliveryapp.domain.dto.request.AuthenticationRequestDto;
import com.demo.deliveryapp.domain.entity.Member;
import com.demo.deliveryapp.domain.enums.SpecificExceptionCode;
import com.demo.deliveryapp.exception.NotExistMemberException;
import com.demo.deliveryapp.exception.UnauthorizedException;
import com.demo.deliveryapp.repository.MemberRepository;
import com.demo.deliveryapp.security.JwtTokenProvider;
import com.demo.deliveryapp.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

/**
 * @author jhkim
 * @since 2023-02-09
 *
 */

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	private final MemberRepository memberRepository;
	private final JwtTokenProvider jwtTokenProvider;

	private final PasswordEncoder passwordEncoder;

	@Override
	public AuthenticationResponseDto login(AuthenticationRequestDto authenticationRequestDto) {
		Member member = memberRepository.findByMemberEmail(authenticationRequestDto.getEmail())
			.orElseThrow(NotExistMemberException::new);

		if (!passwordEncoder.matches(authenticationRequestDto.getPassword(), member.getMemberPassword())) {
			throw new UnauthorizedException(SpecificExceptionCode.UNAUTHORIZED_MEMBER);
		}
		return new AuthenticationResponseDto(jwtTokenProvider.createToken(member.getMemberEmail()));
	}

}
