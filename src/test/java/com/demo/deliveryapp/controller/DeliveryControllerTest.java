package com.demo.deliveryapp.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.demo.deliveryapp.config.SecurityConfig;
import com.demo.deliveryapp.domain.dto.response.DeliveryResDto;
import com.demo.deliveryapp.domain.enums.DeliveryStatus;
import com.demo.deliveryapp.domain.enums.OrderPlatform;
import com.demo.deliveryapp.security.JwtAuthenticationFilter;
import com.demo.deliveryapp.service.DeliveryService;
import com.demo.deliveryapp.service.impl.DeliveryServiceImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author jhkim
 * @since 2023/02/10
 *
 */
@WebMvcTest(value = DeliveryController.class, excludeFilters = {
	@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class),
	@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtAuthenticationFilter.class)
})
@WithMockUser(username = "test01",password = "test")
class DeliveryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DeliveryServiceImpl deliveryService;

	@Test
	@DisplayName("배송조회 테스트")
	void getDeliveryList() throws Exception {
		//given
		String startDate = "2023-02-10";
		String endDate = "2023-02-13";
		List<DeliveryResDto> dtoList = new ArrayList<>();
		DeliveryResDto dto = new DeliveryResDto();
		dtoList.add(dto);
		dto.setAddress("서울시 성동구");
		dto.setOrderPlatform(OrderPlatform.YOGIYO);
		dto.setDeliveryDt(LocalDateTime.now());
		dto.setDeliveryStatus(DeliveryStatus.NOT_STARTED);

		given(deliveryService.getDeliveryList(startDate, endDate))
			.willReturn(dtoList);

		//when-then
		mockMvc.perform(
			get("/api/delivery")
				.param("startDate", startDate)
				.param("endDate", endDate)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andDo(print());
	}
}