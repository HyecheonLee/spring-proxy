package com.hyecheon.springadvanced.config


import com.hyecheon.springadvanced.app.proxy.v1.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/22
 */
@Configuration
class ProxyAppV1Config {

	@Bean
	fun orderControllerV1(): OrderControllerV1 = run {
		OrderControllerV1Impl(orderServiceV1())
	}

	@Bean
	fun orderServiceV1(): OrderServiceV1 = run {
		OrderServiceV1Impl(orderRepositoryV1())
	}

	@Bean
	fun orderRepositoryV1(): OrderRepositoryV1 = run {
		OrderRepositoryV1Impl()
	}
}