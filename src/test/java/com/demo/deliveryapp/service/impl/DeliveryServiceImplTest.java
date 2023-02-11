package com.demo.deliveryapp.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.demo.deliveryapp.domain.dto.response.DeliveryResDto;
import com.demo.deliveryapp.domain.entity.Delivery;
import com.demo.deliveryapp.repository.DeliveryRepository;

/**
 * @author jhkim
 * @since 2023/02/11
 *
 */
@ExtendWith(MockitoExtension.class)
public class DeliveryServiceImplTest {
	@Mock
	private DeliveryRepository deliveryRepository;

	@InjectMocks
	private DeliveryServiceImpl deliveryService;

	@Test
	void getDeliveryList() {
		String startDate = "2022-01-01";
		String endDate = "2022-01-03";

		LocalDate startLocalDate = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
		LocalDate endLocalDate = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);

		Delivery delivery1 = new Delivery();
		Delivery delivery2 = new Delivery();

		when(deliveryRepository.findDeliveriesByDeliveryDtBetween(startLocalDate.atStartOfDay(), endLocalDate.atTime(
			LocalTime.MAX)))
			.thenReturn(Arrays.asList(delivery1, delivery2));

		List<DeliveryResDto> actualResult = deliveryService.getDeliveryList(startDate, endDate);

		assertEquals(2, actualResult.size());
	}
}