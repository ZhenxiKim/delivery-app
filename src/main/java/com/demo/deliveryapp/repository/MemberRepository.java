package com.demo.deliveryapp.repository;

import java.util.Optional;

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
	Optional<Member> findByMemberEmail(String email);

	Optional<Member> findByMemberNo(Long memberNo);
}
