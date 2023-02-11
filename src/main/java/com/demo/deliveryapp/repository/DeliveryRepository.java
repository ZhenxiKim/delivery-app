package com.demo.deliveryapp.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.deliveryapp.domain.entity.Delivery;

/**
 * @author jhkim
 * @since 2023/02/10
 *
 */
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
	List<Delivery> findDeliveriesByDeliveryDtBetween(LocalDateTime startDate, LocalDateTime endDate);
}
