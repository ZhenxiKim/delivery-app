package com.demo.deliveryapp.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.deliveryapp.domain.dto.request.DeliveryUpdateReqDto;
import com.demo.deliveryapp.domain.dto.response.DeliveryResDto;
import com.demo.deliveryapp.domain.entity.Delivery;
import com.demo.deliveryapp.domain.entity.Member;
import com.demo.deliveryapp.domain.enums.DeliveryStatus;
import com.demo.deliveryapp.exception.DataNotFoundException;
import com.demo.deliveryapp.exception.DeliveryStatusUnchangeableException;
import com.demo.deliveryapp.exception.OverSelectedDateGapException;
import com.demo.deliveryapp.repository.DeliveryRepository;
import com.demo.deliveryapp.repository.MemberRepository;
import com.demo.deliveryapp.service.DeliveryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jhkim
 * @since 2023/02/10
 *
 */
@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class DeliveryServiceImpl implements DeliveryService {
	private final DeliveryRepository deliveryRepository;
	private final MemberRepository memberRepository;

	@Override
	public List<DeliveryResDto> getDeliveryList(Long memberNo, String startDate, String endDate) {

		LocalDate startLocalDate = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
		LocalDate endLocalDate = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);
		checkDateGap(startLocalDate, endLocalDate);

		Member member = memberRepository.findByMemberNo(memberNo)
			.orElseThrow(IllegalArgumentException::new);

		List<Delivery> deliveryList
			= this.deliveryRepository.findDeliveriesByMemberAndDeliveryDtBetween(member, startLocalDate.atStartOfDay(),
			endLocalDate.atTime(LocalTime.MAX));

		assert deliveryList != null;

		return deliveryList.stream()
			.map(delivery -> {
				DeliveryResDto resDto = new DeliveryResDto();
				resDto.entityToDto(delivery);
				return resDto;
			})
			.collect(Collectors.toList());
	}

	@Override
	public DeliveryResDto updateDelivery(Long memberNo, DeliveryUpdateReqDto dto, Long deliveryNo) {
		String address = dto.getAddress();

		Delivery delivery = deliveryRepository.findByDeliveryNo(deliveryNo)
			.orElseThrow(DataNotFoundException::new);

		if (!delivery.getMember().getMemberNo().equals(memberNo)) {
			throw new IllegalArgumentException();
		}

		if (DeliveryStatus.NOT_STARTED != delivery.getDeliveryStatus()) {
			throw new DeliveryStatusUnchangeableException();
		}

		delivery.updateAddress(address);
		DeliveryResDto resDto = new DeliveryResDto();
		return resDto.entityToDto(deliveryRepository.save(delivery));
	}

	/**
	 * ?????? ?????? ?????? validation
	 * @param startDate
	 * @param endDate
	 */
	private void checkDateGap(LocalDate startDate, LocalDate endDate) {
		Period period = Period.between(startDate, endDate);
		if (period.getDays() > 3) {
			throw new OverSelectedDateGapException();
		}
	}
}
