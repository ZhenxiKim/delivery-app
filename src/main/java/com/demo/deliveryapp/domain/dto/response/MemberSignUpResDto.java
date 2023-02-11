package com.demo.deliveryapp.domain.dto.response;

import java.time.LocalDateTime;

import com.demo.deliveryapp.domain.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author jhkim
 * @since 2023/02/09
 * 회원가입 response DTO
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberSignUpResDto {

	private String email;

	private String password;

	private String name;

	private LocalDateTime createdDateTime;

	public static MemberSignUpResDto entityToDto(Member member) {
		return MemberSignUpResDto.builder()
			.email(member.getMemberEmail())
			.name(member.getMemberName())
			.build();
	}
}
