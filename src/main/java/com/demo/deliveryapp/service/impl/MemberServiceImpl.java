package com.demo.deliveryapp.service.impl;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.deliveryapp.domain.dto.MemberSignUpReqDto;
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
	public Member signUp(MemberSignUpReqDto memberSignUpReqDto) {

		boolean isExist = isExistMember(memberSignUpReqDto);
		Member memberEntity = new Member();
		if(isExist) {
			memberEntity.setMemberName(memberSignUpReqDto.getName());
			memberEntity.setMemberEmail(memberSignUpReqDto.getEmail());
			memberEntity.setMemberPassword(passwordEncoder.encode(memberSignUpReqDto.getPassword()));
			memberEntity.setMemberSignupDate(LocalDateTime.now());
			return memberRepository.save(memberEntity);
		}
		return memberEntity;
	}


	@Override
	public boolean isExistMember(MemberSignUpReqDto memberSignUpReqDto) {
		Member member = memberRepository.findByMemberEmail(memberSignUpReqDto.getEmail());
		if(member != null) {
			throw new ExistMemberException();
		}
		return true;
	}

}
