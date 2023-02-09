package com.demo.deliveryapp.exception;

import com.demo.deliveryapp.domain.enums.SpecificExceptionCode;

/**
 * @author jhkim
 * @since 2023-02-08
 *
 */
public class NotExistMemberException extends CustomException {

	public NotExistMemberException() {
		super(SpecificExceptionCode.NOT_EXIST_MEMBER_EXCEPTION);
	}

}
