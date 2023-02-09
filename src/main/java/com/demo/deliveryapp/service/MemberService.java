package com.demo.deliveryapp.service;

import com.demo.deliveryapp.domain.dto.MemberSignUpReqDto;
import com.demo.deliveryapp.domain.entity.Member;

/**
 * @author jhkim
 * @since 2023-02-09
 *
 */
public interface MemberService {
	Member signUp(MemberSignUpReqDto memberSignUpReqDto);
	boolean isExistMember(MemberSignUpReqDto memberSignUpReqDto);
}
