package com.demo.deliveryapp.service;

import com.demo.deliveryapp.domain.dto.request.MemberSignUpReqDto;
import com.demo.deliveryapp.domain.dto.response.MemberSignUpResDto;

/**
 * @author jhkim
 * @since 2023-02-09
 *
 */
public interface MemberService {
	MemberSignUpResDto signUp(MemberSignUpReqDto memberSignUpReqDto);
}
