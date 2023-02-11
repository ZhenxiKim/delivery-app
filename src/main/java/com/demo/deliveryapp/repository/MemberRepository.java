package com.demo.deliveryapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.deliveryapp.domain.entity.Member;

/**
 * @author jhkim
 * @since 2023-02-08
 * 회원 repository
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
	Member findByMemberEmail(String email);
	Member findByMemberNo(Long memberNo);
}
