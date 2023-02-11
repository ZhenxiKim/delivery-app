package com.demo.deliveryapp.service;

import java.util.List;

import com.demo.deliveryapp.domain.dto.response.DeliveryResDto;

/**
 * @author jhkim
 * @since 2023/02/10
 *
 */
public interface DeliveryService {
	List<DeliveryResDto> getDeliveryList(Long memberNo, String startDate, String endDate);
}
