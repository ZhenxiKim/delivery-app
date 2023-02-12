package com.demo.deliveryapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.deliveryapp.domain.dto.request.AuthenticationRequestDto;
import com.demo.deliveryapp.exception.UnauthorizedException;
import com.demo.deliveryapp.service.AuthenticationService;

import io.swagger.v3.oas.annotations.Operation;
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

	@Operation(summary = "로그인 API")
	@PostMapping("/login")
	public ResponseEntity<?> authentication(@RequestBody AuthenticationRequestDto authenticationRequestDto) throws
		UnauthorizedException {
		return ResponseEntity.status(HttpStatus.OK).body(authenticationService.login(authenticationRequestDto));
	}
}
