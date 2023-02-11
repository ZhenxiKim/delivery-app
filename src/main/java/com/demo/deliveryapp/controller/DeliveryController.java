package com.demo.deliveryapp.controller;

import java.util.List;

import javax.validation.constraints.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.deliveryapp.domain.dto.response.DeliveryResDto;
import com.demo.deliveryapp.service.DeliveryService;

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

	@GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DeliveryResDto>> getDeliveryList(@Pattern(regexp = DATE_REGEX) @RequestParam String startDate,
		@Pattern(regexp = DATE_REGEX) @RequestParam String endDate) {
		return ResponseEntity.status(HttpStatus.OK)
			.body(deliveryService.getDeliveryList(startDate, endDate));
	}
}
