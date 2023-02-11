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
	INVALID_JWT_TOKEN(500,'M',"유효하지 않은 토큰입니다."),
	EXIST_MEMBER_EXCEPTION(500,'M',"이미 가입된 회원의 정보입니다."),
	VALIDATION_EXCEPTION(400,'M',"입력값이 잘못되었습니다."),
	OVER_SELECTED_DATE_GAP_EXCEPTION(400,'D',"조회기간은 최대 3일까지 가능합니다."),
	DELIVERY_STATUS_UNCHANGEABLE(500,'D',"변경 불가한 배달 상태입니다.");
	private final int httpCode;
	private final char exceptionGroup;//exception 발생 주체그룹
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
