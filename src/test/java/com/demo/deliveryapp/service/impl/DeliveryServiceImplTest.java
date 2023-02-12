package com.demo.deliveryapp.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.demo.deliveryapp.domain.dto.request.DeliveryUpdateReqDto;
import com.demo.deliveryapp.domain.dto.response.DeliveryResDto;
import com.demo.deliveryapp.domain.entity.Delivery;
import com.demo.deliveryapp.domain.entity.Member;
import com.demo.deliveryapp.domain.enums.OrderPlatform;
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
	void 배달_조회_test() {
		String startDate = "2022-01-01";
		String endDate = "2022-01-03";

		LocalDate startLocalDate = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
		LocalDate endLocalDate = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);

		Member member = new Member();
		long memberNo = 1L;
		when(memberRepository.findByMemberNo(memberNo)).thenReturn(Optional.of(member));

		Delivery delivery1 = Delivery.builder()
			.member(member)
			.address("test")
			.orderPlatform(OrderPlatform.BAEMIN)
			.build();
		Delivery delivery2 = Delivery.builder()
			.member(member)
			.address("test")
			.orderPlatform(OrderPlatform.BAEMIN)
			.build();

		when(deliveryRepository.findDeliveriesByMemberAndDeliveryDtBetween(member, startLocalDate.atStartOfDay(),
			endLocalDate.atTime(
				LocalTime.MAX)))
			.thenReturn(Arrays.asList(delivery1, delivery2));

		List<DeliveryResDto> actualResult = deliveryService.getDeliveryList(memberNo, startDate, endDate);

		assertEquals(2, actualResult.size());
	}

	@Test
	void 배달_정보_수정_exception_test() {
		Long deliveryNo = 1L;
		Long memberNo = 1L;

		Member member = Mockito.mock(Member.class);

		Delivery delivery = Delivery.builder()
			.member(member)
			.address("before address")
			.orderPlatform(OrderPlatform.YOGIYO)
			.build();

		delivery.inDelivery();

		DeliveryUpdateReqDto dto = new DeliveryUpdateReqDto();
		dto.setAddress("modify Address");

		when(deliveryRepository.findByDeliveryNo(any(Long.class))).thenReturn(Optional.of(delivery));
		when(member.getMemberNo()).thenReturn(1L);

		assertThrows(DeliveryStatusUnchangeableException.class, () ->
			deliveryService.updateDelivery(memberNo, dto, deliveryNo));
	}

	@Test
	void 다른_회원의_배달_정보_수정_테스트() {
		Long deliveryNo = 1L;
		Long memberNo = 1L;

		DeliveryUpdateReqDto dto = new DeliveryUpdateReqDto();
		dto.setAddress("Address");

		Member member = Mockito.mock(Member.class);

		Delivery delivery = Delivery.builder()
			.member(member)
			.address("test")
			.orderPlatform(OrderPlatform.BAEMIN)
			.build();

		when(deliveryRepository.findByDeliveryNo(any(Long.class))).thenReturn(Optional.of(delivery));
		when(member.getMemberNo()).thenReturn(2L);

		assertThrows(IllegalArgumentException.class, () ->
			deliveryService.updateDelivery(memberNo, dto, deliveryNo));
	}

	@Test
	void 배달_정보_수정_테스트() {
		Long deliveryNo = 1L;
		Long memberNo = 1L;

		Member member = Mockito.mock(Member.class);
		Delivery delivery = Delivery.builder()
			.member(member)
			.address("Address")
			.orderPlatform(OrderPlatform.BAEMIN)
			.build();

		String testModefiedTxt = "수정 테스트";

		DeliveryUpdateReqDto dto = new DeliveryUpdateReqDto();
		dto.setAddress(testModefiedTxt);

		when(deliveryRepository.save(any(Delivery.class))).thenReturn(delivery);
		when(deliveryRepository.findByDeliveryNo(any(Long.class))).thenReturn(Optional.of(delivery));
		when(member.getMemberNo()).thenReturn(1L);

		DeliveryResDto deliveryResDto = deliveryService.updateDelivery(memberNo, dto, deliveryNo);

		assertEquals(deliveryResDto.getAddress(), testModefiedTxt);
	}
}