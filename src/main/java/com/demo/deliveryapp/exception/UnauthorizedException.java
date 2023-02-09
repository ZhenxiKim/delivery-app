package com.demo.deliveryapp.exception;

import com.demo.deliveryapp.domain.enums.SpecificExceptionCode;

/**
 * @author jhkim
 * @since 2023-02-09
 *
 */
public class UnauthorizedException extends CustomException {

	public UnauthorizedException(SpecificExceptionCode notExistMemberException) {
		super(notExistMemberException);
	}
}
