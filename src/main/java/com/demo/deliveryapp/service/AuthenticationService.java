package com.demo.deliveryapp.service;

import com.demo.deliveryapp.domain.dto.AuthenticationRequestDto;
import com.demo.deliveryapp.domain.dto.AuthenticationResponseDto;

/**
 * @author jhkim
 * @since 2023-02-09
 *
 */
public interface AuthenticationService {
	AuthenticationResponseDto login(AuthenticationRequestDto authenticationRequestDto);

}
