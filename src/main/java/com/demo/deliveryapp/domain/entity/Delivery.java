package com.demo.deliveryapp.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.demo.deliveryapp.domain.dto.response.DeliveryResDto;
import com.demo.deliveryapp.domain.enums.DeliveryStatus;
import com.demo.deliveryapp.domain.enums.OrderPlatform;

import lombok.Getter;
import lombok.Setter;

/**
 * @author jhkim
 * @since 2023/02/10
 *
 */
@Getter
@Setter
@Entity
public class Delivery extends BaseTimeEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long deliveryNo;

	private String address;

	@Enumerated(value = EnumType.STRING)
	private OrderPlatform orderPlatform;

	@Enumerated(value = EnumType.STRING)
	private DeliveryStatus deliveryStatus;

	private LocalDateTime deliveryDt;

	public static DeliveryResDto entityListToDtoList(Delivery delivery) {
		DeliveryResDto dto = new DeliveryResDto();
		dto.setAddress(delivery.getAddress());
		dto.setOrderPlatform(delivery.getOrderPlatform());
		dto.setDeliveryDt(delivery.getDeliveryDt());
		dto.setDeliveryStatus(delivery.getDeliveryStatus());
		return dto;
	}
}