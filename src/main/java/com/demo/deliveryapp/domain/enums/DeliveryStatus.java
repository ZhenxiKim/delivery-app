package com.demo.deliveryapp.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author jhkim
 * @since 2023/02/10
 * 배달상태 enum
 */
@AllArgsConstructor
@Getter
public enum DeliveryStatus {
	NOT_STARTED("배달대기"),
	IN_PROGRESS("배달중"),
	DONE("배달완료");

	private final String status;

}
