package com.demo.deliveryapp.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author jhkim
 * @since 2023/02/10
 *	주문 플랫폼
 */
@AllArgsConstructor
@Getter
public enum OrderPlatform {

	BAEMIN("배민"),
	COUPANG_EATS("쿠팡잇츠"),
	YOGIYO("요기요");

	private final String orderPlatformName;


}
