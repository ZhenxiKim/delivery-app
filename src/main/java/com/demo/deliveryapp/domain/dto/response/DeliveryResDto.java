package com.demo.deliveryapp.domain.dto.response;

import java.time.LocalDateTime;

import com.demo.deliveryapp.domain.entity.Delivery;
import com.demo.deliveryapp.domain.enums.DeliveryStatus;
import com.demo.deliveryapp.domain.enums.OrderPlatform;

import lombok.Data;

/**
 * @author jhkim
 * @since 2023/02/10
 *
 */
@Data
public class DeliveryResDto {
	private String address;
	private OrderPlatform orderPlatform;
	private DeliveryStatus deliveryStatus;
	private LocalDateTime deliveryDt;

	public DeliveryResDto entityToDto(Delivery delivery) {
		this.address = delivery.getAddress();
		this.orderPlatform = delivery.getOrderPlatform();
		this.deliveryDt = delivery.getDeliveryDt();
		this.deliveryStatus = delivery.getDeliveryStatus();
		return this;
	}
}
