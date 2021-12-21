package com.hyecheon.springadvanced.config


import com.hyecheon.springadvanced.app.proxy.v2.OrderControllerV2
import com.hyecheon.springadvanced.app.proxy.v2.OrderRepositoryV2
import com.hyecheon.springadvanced.app.proxy.v2.OrderServiceV2
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/22
 */
@Configuration
class ProxyAppV2Config {

	@Bean
	fun orderControllerV2() = run {
		OrderControllerV2(orderServiceV2())
	}

	@Bean
	fun orderServiceV2() = run {
		OrderServiceV2(orderRepositoryV2())
	}

	@Bean
	fun orderRepositoryV2() = run {
		OrderRepositoryV2()
	}
}