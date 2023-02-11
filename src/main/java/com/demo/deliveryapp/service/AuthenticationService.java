package com.demo.deliveryapp.service;

import com.demo.deliveryapp.domain.dto.AuthenticationResponseDto;
import com.demo.deliveryapp.domain.dto.request.AuthenticationRequestDto;

/**
 * @author jhkim
 * @since 2023-02-09
 *
 */
public interface AuthenticationService {
	AuthenticationResponseDto login(AuthenticationRequestDto authenticationRequestDto);

}
