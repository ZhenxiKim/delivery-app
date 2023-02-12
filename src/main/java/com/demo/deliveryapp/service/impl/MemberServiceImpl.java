package com.demo.deliveryapp.service.impl;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.deliveryapp.domain.dto.request.MemberSignUpReqDto;
import com.demo.deliveryapp.domain.dto.response.MemberSignUpResDto;
import com.demo.deliveryapp.domain.entity.Member;
import com.demo.deliveryapp.exception.ExistMemberException;
import com.demo.deliveryapp.repository.MemberRepository;
import com.demo.deliveryapp.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jhkim
 * @since 2022-10-28
 *
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public MemberSignUpResDto signUp(MemberSignUpReqDto memberSignUpReqDto) {
		isExistMember(memberSignUpReqDto);

		Member memberEntity = Member.builder()
			.memberName(memberSignUpReqDto.getName())
			.memberEmail(memberSignUpReqDto.getEmail())
			.memberPassword(passwordEncoder.encode(memberSignUpReqDto.getPassword()))
			.build();

		return Stream.of(memberRepository.save(memberEntity))
			.map(MemberSignUpResDto::entityToDto)
			.collect(Collectors.toList())
			.get(0);
	}

	/**
	 * 존재하는 회원인지 검사
	 * @param memberSignUpReqDto
	 */
	private void isExistMember(MemberSignUpReqDto memberSignUpReqDto) {
		memberRepository.findByMemberEmail(memberSignUpReqDto.getEmail())
			.ifPresent(member -> {
				throw new ExistMemberException();
			});
	}
}
