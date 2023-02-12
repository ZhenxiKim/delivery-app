package com.demo.deliveryapp.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.deliveryapp.domain.entity.Delivery;
import com.demo.deliveryapp.domain.entity.Member;

/**
 * @author jhkim
 * @since 2023/02/10
 *
 */
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
	List<Delivery> findDeliveriesByMemberAndDeliveryDtBetween(Member memberNo, LocalDateTime startDate,
		LocalDateTime endDate);

	Optional<Delivery> findByDeliveryNo(Long deliveryNo);
}
