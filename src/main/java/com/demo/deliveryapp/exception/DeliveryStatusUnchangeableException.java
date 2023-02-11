package com.demo.deliveryapp.exception;

import com.demo.deliveryapp.domain.enums.SpecificExceptionCode;

/**
 * @author jhkim
 * @since 2023/02/11
 *
 */
public class DeliveryStatusUnchangeableException extends CustomException {
	public DeliveryStatusUnchangeableException() {
		super(SpecificExceptionCode.DELIVERY_STATUS_UNCHANGEABLE);
	}
}
