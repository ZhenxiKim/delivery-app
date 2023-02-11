package com.demo.deliveryapp.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.deliveryapp.domain.dto.request.MemberSignUpReqDto;
import com.demo.deliveryapp.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jhkim
 * @since 2023-02-08
 * 회원 controller
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/member")
public class MemberController {
	private final MemberService memberService;

	@PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> signUp(@Valid @RequestBody MemberSignUpReqDto memberSignUpReqDto) {
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(memberService.signUp(memberSignUpReqDto));
	}
}
