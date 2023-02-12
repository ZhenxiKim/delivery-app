package com.demo.deliveryapp.exception;

import com.demo.deliveryapp.domain.enums.SpecificExceptionCode;

/**
 * @author jhkim
 * @since 2023/02/10
 *
 */
public class OverSelectedDateGapException extends CustomException {
	public OverSelectedDateGapException() {
		super(SpecificExceptionCode.OVER_SELECTED_DATE_GAP_EXCEPTION);
	}
}
