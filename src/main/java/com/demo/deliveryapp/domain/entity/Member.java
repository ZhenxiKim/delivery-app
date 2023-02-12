package com.demo.deliveryapp.domain.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import io.jsonwebtoken.lang.Assert;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author jhkim
 * @since 2023-02-08
 * 회원 entity
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Member extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memberNo;

	private String memberEmail;//회원 이메일

	private String memberPassword;//회원 비밀번호

	private String memberName;//회원이름

	private LocalDateTime memberSignupDate;

	@OneToMany(mappedBy = "member")
	private List<Delivery> deliveryList = new ArrayList<>();

	@Builder
	public Member(String memberEmail, String memberPassword, String memberName) {

		Assert.isTrue(memberEmail != null, "email can not be null");
		Assert.isTrue(memberPassword != null, "memberPassword can not be null");
		Assert.isTrue(memberName != null, "memberName can not be null");

		this.memberEmail = memberEmail;
		this.memberPassword = memberPassword;
		this.memberName = memberName;
		this.memberSignupDate = LocalDateTime.now();
	}
}
