package com.demo.deliveryapp.exception;

import com.demo.deliveryapp.domain.enums.SpecificExceptionCode;

/**
 * @author jhkim
 * @since 2023-02-08
 *
 */
public class CustomException extends RuntimeException{

	private final SpecificExceptionCode exceptionCode;

	public CustomException(SpecificExceptionCode exceptionCode){
		super(exceptionCode.getMsgDetail());
		this.exceptionCode = exceptionCode;
	}

	public SpecificExceptionCode getExceptionCode(){
		return exceptionCode;
	}
}
