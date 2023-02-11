package com.demo.deliveryapp.domain.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author jhkim
 * @since 2023-02-08
 * 회원 entity
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Member extends BaseTimeEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memberNo;

	private String memberEmail;//회원 이메일

	private String memberPassword;//회원 비밀번호

	private String memberName;//회원이름

	private LocalDateTime memberSignupDate;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "member_no")
	private List<Delivery> deliveryList = new ArrayList<>();
}
