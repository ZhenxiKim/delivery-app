package com.demo.deliveryapp.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * @author jhkim
 * @since 2023/02/11
 *
 */
@OpenAPIDefinition(
	info = @Info(
		title = "배달 서비스 API 명세서",
		description = "API 명세서",
		version = "v1"
	)
)
@Configuration
public class OpenApiConfig {

}
