package com.demo.deliveryapp.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.deliveryapp.domain.dto.AuthenticatedUser;
import com.demo.deliveryapp.domain.entity.Member;
import com.demo.deliveryapp.exception.NotExistMemberException;
import com.demo.deliveryapp.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

/**
 * @author jhkim
 * @since 2022-10-29
 *
 */
@RequiredArgsConstructor
@Service
public class CustomUserDetailServiceImpl implements UserDetailsService {
	private final MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberRepository.findByMemberEmail(username)
			.orElseThrow(NotExistMemberException::new);
		return AuthenticatedUser.builder()
			.memberNo(member.getMemberNo())
			.memberEmail(member.getMemberEmail())
			.memberName(member.getMemberName())
			.build();
	}
}
