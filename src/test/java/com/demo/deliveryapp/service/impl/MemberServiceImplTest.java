package com.demo.deliveryapp.service.impl;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.demo.deliveryapp.domain.dto.MemberSignUpReqDto;
import com.demo.deliveryapp.domain.entity.Member;
import com.demo.deliveryapp.repository.MemberRepository;
import com.demo.deliveryapp.service.MemberService;

/**
 * @author jhkim
 * @since 2023/02/09
 *
 */
@SpringBootTest
class MemberServiceImplTest {


	@Autowired
	private MemberService memberService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@DisplayName("회원가입 테스트")
	@Rollback(value = true)
	@Test
	void signUp() {
		String memberEmail = "test@gamil.com";
		String memberName = "tester";
		String password = "test1234";

		//given
		MemberSignUpReqDto memberSignUpReqDto = MemberSignUpReqDto.builder()
			.email(memberEmail)
			.name(memberName)
			.password(passwordEncoder.encode(password))
			.build();

		//when
		Member member = memberService.signUp(memberSignUpReqDto);

		//then
		assertThat(member.getMemberEmail()).isEqualTo(memberSignUpReqDto.getEmail());

	}

	@Test
	void isExistMember() {
	}
}