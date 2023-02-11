package com.demo.deliveryapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.deliveryapp.domain.dto.request.AuthenticationRequestDto;
import com.demo.deliveryapp.domain.enums.SpecificExceptionCode;
import com.demo.deliveryapp.exception.UnauthorizedException;
import com.demo.deliveryapp.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

/**
 * @author jhkim
 * @since 2023-02-09
 * 인증, 허가 controller
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

	private final AuthenticationService authenticationService;

	@PostMapping("/login")
	public ResponseEntity<?> authentication(@RequestBody AuthenticationRequestDto authenticationRequestDto) throws UnauthorizedException{
		try {
			return ResponseEntity.status(HttpStatus.OK).body(authenticationService.login(authenticationRequestDto));
		} catch (Exception e) {
			throw new UnauthorizedException(SpecificExceptionCode.NOT_EXIST_MEMBER_EXCEPTION);
		}
	}
}
