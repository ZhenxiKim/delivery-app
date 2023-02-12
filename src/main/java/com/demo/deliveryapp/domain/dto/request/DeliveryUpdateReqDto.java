package com.demo.deliveryapp.domain.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

/**
 * @author jhkim
 * @since 2023/02/11
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class DeliveryUpdateReqDto {
	@NonNull
	private String address;
}
