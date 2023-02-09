package com.demo.deliveryapp.domain.enums;

import lombok.Getter;

/**
 * @author jhkim
 * @since 2023-02-08
 * enum code
 */
@Getter
public enum SpecificExceptionCode {
	NOT_EXIST_MEMBER_EXCEPTION(500,'M',"가입되지않은 회원의 정보입니다."),
	EXIST_MEMBER_EXCEPTION(500,'M',"이미 가입된 회원의 정보입니다."),
	VALIDATION_EXCEPTION(400,'M',"입력값이 잘못되었습니다.");
	private final int httpCode;
	private final char exceptionGroup;
	private final String msgDetail;

	SpecificExceptionCode(final int httpCode, final char exceptionGroup, final String msgDetail) {
		this.httpCode = httpCode;
		this.exceptionGroup = exceptionGroup;
		this.msgDetail = msgDetail;
	}

	public String getExceptionCode() {
		return exceptionGroup + Integer.toString(httpCode);
	}
}
