package com.demo.deliveryapp.service.impl;

import org.springframework.stereotype.Service;

import com.demo.deliveryapp.domain.dto.request.AuthenticationRequestDto;
import com.demo.deliveryapp.domain.dto.AuthenticationResponseDto;
import com.demo.deliveryapp.domain.entity.Member;
import com.demo.deliveryapp.exception.NotExistMemberException;
import com.demo.deliveryapp.repository.MemberRepository;
import com.demo.deliveryapp.security.JwtTokenProvider;
import com.demo.deliveryapp.service.AuthenticationService;

import lombok.RequiredArgsConstructor;
/**
 * @author jhkim
 * @since 2023-02-09
 *
 */

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public AuthenticationResponseDto login(AuthenticationRequestDto authenticationRequestDto) {
        Member member = memberRepository.findByMemberEmail(authenticationRequestDto.getEmail());
        if(member == null) {
            throw new NotExistMemberException();
        }
        return new AuthenticationResponseDto(jwtTokenProvider.createToken(member.getMemberEmail()));
    }

}
