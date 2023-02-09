package com.demo.deliveryapp.domain.dto.response;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * @author jhkim
 * @since 2023/02/09
 * 회원가입 response DTO
 */
@Getter
@Setter
public class MemberSignUpResDto {

	private String email;

	private String password;

	private String name;

	private LocalDateTime createdDateTime;
}
