package com.demo.deliveryapp.exception;

import com.demo.deliveryapp.domain.enums.SpecificExceptionCode;

/**
 * @author jhkim
 * @since 2023-02-08
 *
 */
public class ExistMemberException extends CustomException {
	public ExistMemberException() {
		super(SpecificExceptionCode.EXIST_MEMBER_EXCEPTION);
	}
}
