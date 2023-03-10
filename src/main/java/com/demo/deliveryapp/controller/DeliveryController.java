package com.demo.deliveryapp.controller;

import java.util.List;

import javax.validation.constraints.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.deliveryapp.domain.dto.AuthenticatedUser;
import com.demo.deliveryapp.domain.dto.request.DeliveryUpdateReqDto;
import com.demo.deliveryapp.domain.dto.response.DeliveryResDto;
import com.demo.deliveryapp.domain.enums.SpecificExceptionCode;
import com.demo.deliveryapp.exception.UnauthorizedException;
import com.demo.deliveryapp.service.DeliveryService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jhkim
 * @since 2023/02/10
 * 배달 controller
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {
	private static final String DATE_REGEX = "^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$";
	private final DeliveryService deliveryService;

	@Operation(summary = "배달 조회 API", description = "로그인 후 token 획득 후 조회 가능")
	@GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DeliveryResDto>> getDeliveryList(@AuthenticationPrincipal AuthenticatedUser user,
		@Pattern(regexp = DATE_REGEX) @RequestParam String startDate,
		@Pattern(regexp = DATE_REGEX) @RequestParam String endDate) {
		return ResponseEntity.status(HttpStatus.OK)
			.body(deliveryService.getDeliveryList(user.getMemberNo(), startDate, endDate));
	}

	@Operation(summary = "배달 정보 수정 API", description = "로그인 후 token 획득 후 조회 가능")
	@PutMapping(path = "/{deliveryNo}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DeliveryResDto> updateDelivery(@AuthenticationPrincipal AuthenticatedUser user,
		@RequestBody DeliveryUpdateReqDto dto, @PathVariable Long deliveryNo) {
		return ResponseEntity.status(HttpStatus.OK)
			.body(deliveryService.updateDelivery(user.getMemberNo(), dto, deliveryNo));
	}
}
