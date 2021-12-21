package com.hyecheon.springadvanced.config


import com.hyecheon.springadvanced.app.proxy.v1.OrderControllerV1Impl
import com.hyecheon.springadvanced.app.proxy.v1.OrderRepositoryV1Impl
import com.hyecheon.springadvanced.app.proxy.v1.OrderServiceV1Impl
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
	fun orderControllerV1() = run {
		OrderControllerV1Impl(orderServiceV1())
	}

	@Bean
	fun orderServiceV1() = run {
		OrderServiceV1Impl(orderRepositoryV1())
	}

	@Bean
	fun orderRepositoryV1() = run {
		OrderRepositoryV1Impl()
	}
}