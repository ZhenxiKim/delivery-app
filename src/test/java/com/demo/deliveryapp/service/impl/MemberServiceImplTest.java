package com.demo.deliveryapp.service.impl;

import static org.mockito.AdditionalAnswers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.demo.deliveryapp.domain.dto.request.MemberSignUpReqDto;
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
	@DisplayName("회원가입 테스트")
	void signUp() {
		String pwd = "!testPwd1234!";
		Mockito.when(passwordEncoder.encode(any())).thenReturn(pwd);
		Mockito.when(memberRepository.save(any(Member.class))).then(returnsFirstArg());

		MemberSignUpResDto dto = memberService.signUp(
			new MemberSignUpReqDto("tester@gmail.com", "!testPwd1234!", "tester"));

		Assertions.assertEquals(dto.getName(), "tester");
		Assertions.assertEquals(dto.getEmail(), "tester@gmail.com");
	}
}