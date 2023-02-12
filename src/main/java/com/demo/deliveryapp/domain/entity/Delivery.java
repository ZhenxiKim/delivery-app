package com.demo.deliveryapp.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.StringUtils;

import com.demo.deliveryapp.domain.enums.DeliveryStatus;
import com.demo.deliveryapp.domain.enums.OrderPlatform;

import io.jsonwebtoken.lang.Assert;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author jhkim
 * @since 2023/02/10
 *
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long deliveryNo;

	private String address;

	@Enumerated(value = EnumType.STRING)
	private OrderPlatform orderPlatform;

	@Enumerated(value = EnumType.STRING)
	private DeliveryStatus deliveryStatus;

	private LocalDateTime deliveryDt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_no")
	private Member member;

	@Builder
	public Delivery(String address, OrderPlatform orderPlatform, Member member) {

		Assert.isTrue(address != null, "address can not be null");
		Assert.isTrue(orderPlatform != null, "orderPlatform can not be null");
		Assert.isTrue(member != null, "member can not be null");

		this.address = address;
		this.orderPlatform = orderPlatform;
		this.deliveryStatus = DeliveryStatus.NOT_STARTED;
		this.deliveryDt = LocalDateTime.now();
		this.member = member;
	}

	public void updateAddress(String address) {
		Assert.isTrue(StringUtils.isNotEmpty(address), "address can not be null");

		this.address = address;
	}

	public void inDelivery() {
		Assert.isTrue(DeliveryStatus.NOT_STARTED == this.deliveryStatus, "Wrong Delivery Status");

		this.deliveryStatus = DeliveryStatus.IN_PROGRESS;
	}

	public void completeDelivery() {
		Assert.isTrue(DeliveryStatus.IN_PROGRESS == this.deliveryStatus, "Wrong Delivery Status");

		this.deliveryStatus = DeliveryStatus.DONE;
	}
}
