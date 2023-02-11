package com.demo.deliveryapp.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.demo.deliveryapp.domain.dto.request.DeliveryUpdateReqDto;
import com.demo.deliveryapp.domain.dto.response.DeliveryResDto;
import com.demo.deliveryapp.domain.entity.Delivery;
import com.demo.deliveryapp.domain.entity.Member;
import com.demo.deliveryapp.domain.enums.DeliveryStatus;
import com.demo.deliveryapp.exception.DeliveryStatusUnchangeableException;
import com.demo.deliveryapp.repository.DeliveryRepository;
import com.demo.deliveryapp.repository.MemberRepository;

/**
 * @author jhkim
 * @since 2023/02/11
 *
 */
@ExtendWith(MockitoExtension.class)
class DeliveryServiceImplTest {
	@Mock
	private DeliveryRepository deliveryRepository;
	@Mock
	private MemberRepository memberRepository;
	@InjectMocks
	private DeliveryServiceImpl deliveryService;

	@Test
	@DisplayName("배달 조회 test")
	void getDeliveryList() {
		String startDate = "2022-01-01";
		String endDate = "2022-01-03";

		LocalDate startLocalDate = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
		LocalDate endLocalDate = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);

		Member member = new Member();
		long memberNo = 1L;
		member.setMemberNo(memberNo);
		when(memberRepository.findByMemberNo(memberNo)).thenReturn(member);

		Delivery delivery1 = new Delivery();
		Delivery delivery2 = new Delivery();

		when(deliveryRepository.findDeliveriesByMemberAndDeliveryDtBetween(member, startLocalDate.atStartOfDay(), endLocalDate.atTime(
			LocalTime.MAX)))
			.thenReturn(Optional.of(Arrays.asList(delivery1, delivery2)));

		List<DeliveryResDto> actualResult = deliveryService.getDeliveryList(memberNo, startDate, endDate);

		assertEquals(2, actualResult.size());
	}

	@Test
	@DisplayName("배달 정보 수정 exception test")
	void updateDeliveryException() {
		Long deliveryNo = 1L;
		DeliveryUpdateReqDto dto = new DeliveryUpdateReqDto();
		dto.setDeliveryNo(deliveryNo);
		dto.setAddress("Address");

		Delivery delivery = new Delivery();
		delivery.setDeliveryNo(deliveryNo);
		delivery.setDeliveryStatus(DeliveryStatus.DONE);

		when(deliveryRepository.findByDeliveryNo(any(Long.class))).thenReturn(delivery);

		assertThrows(DeliveryStatusUnchangeableException.class, () -> {
			deliveryService.updateDelivery(dto);
		});
	}

	@Test
	@DisplayName("배달 정보 수정 test")
	void updateDelivery() {
		Long deliveryNo = 1L;
		Delivery delivery = new Delivery();
		delivery.setDeliveryNo(deliveryNo);
		delivery.setDeliveryStatus(DeliveryStatus.NOT_STARTED);
		delivery.setAddress("Address");

		String testModefiedTxt = "수정 테스트";
		DeliveryUpdateReqDto dto = new DeliveryUpdateReqDto();

		dto.setAddress(testModefiedTxt);
		dto.setDeliveryNo(deliveryNo);

		when(deliveryRepository.save(any(Delivery.class))).thenReturn(delivery);
		when(deliveryRepository.findByDeliveryNo(any(Long.class))).thenReturn(delivery);

		DeliveryResDto deliveryResDto = deliveryService.updateDelivery(dto);

		assertEquals(deliveryResDto.getAddress(), testModefiedTxt);
	}
}