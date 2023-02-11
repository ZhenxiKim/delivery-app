package com.demo.deliveryapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author jhkim
 * @since 2023-02-08
 *
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ErrorResponseDto {
	private String msg;
	private String code;
}
