package com.demo.deliveryapp.domain.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author jhkim
 * @since 2023-02-08
 * 회원가입 dto
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class MemberSignUpReqDto {

	@NotNull(message = "아이디은 필수값 입니다.")
	@Email
	@Size(max = 100, message = "아이디을 100자리 이하로 입력하세요.")
	private String email;

	@NotNull(message = "비밀번호는 필수값 입니다.")
	@Size(min = 12)
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{12,100}", message = "비밀번호는 영어 대,소문자, 숫자, 특수문자 중 3종류 이상으로"
		+ "12자리 이상를 사용하세요.")
	private String password;

	@NotBlank(message = "사용할 이름을 입력해주세요.")
	private String name;
}
