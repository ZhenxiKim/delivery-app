package com.demo.deliveryapp.service.impl;

import static org.mockito.AdditionalAnswers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.demo.deliveryapp.domain.dto.MemberSignUpReqDto;
import com.demo.deliveryapp.domain.dto.response.MemberSignUpResDto;
import com.demo.deliveryapp.domain.entity.Member;
import com.demo.deliveryapp.repository.MemberRepository;

/**
 * @author jhkim
 * @since 2023/02/09
 * member service unit test
 */
class MemberServiceImplTest {

	private MemberRepository memberRepository = Mockito.mock(MemberRepository.class);
	private MemberServiceImpl memberService;
	private PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);

	@BeforeEach
	public void setUpTest() {
		memberService = new MemberServiceImpl(memberRepository, passwordEncoder);
	}

	@Test
	void signUp() {
		Mockito.when(memberRepository.save(any(Member.class))).then(returnsFirstArg());

		MemberSignUpResDto dto = memberService.signUp(new MemberSignUpReqDto("tester@gmail.com", passwordEncoder.encode("testerPw"), "tester"));

		Assertions.assertEquals(dto.getName(),"tester");
		Assertions.assertEquals(dto.getEmail(),"tester@gmail.com");
	}
}