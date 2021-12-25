package com.hyecheon.springadvanced.config.v1_proxy

import com.hyecheon.springadvanced.app.proxy.v2.OrderControllerV2
import com.hyecheon.springadvanced.app.proxy.v2.OrderRepositoryV2
import com.hyecheon.springadvanced.app.proxy.v2.OrderServiceV2
import com.hyecheon.springadvanced.config.v1_proxy.concrete_proxy.OrderControllerConcreteProxy
import com.hyecheon.springadvanced.config.v1_proxy.concrete_proxy.OrderRepositoryConcreteProxy
import com.hyecheon.springadvanced.config.v1_proxy.concrete_proxy.OrderServiceConcreteProxy
import com.hyecheon.springadvanced.trace.logtrace.LogTrace
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/25
 */
@Configuration
class ConcreteProxyConfig {

	@Bean
	fun orderControllerV2(logTrace: LogTrace) = run {
		val orderControllerV2 = OrderControllerV2(orderServiceV2(logTrace))
		OrderControllerConcreteProxy(orderControllerV2, logTrace)
	}

	@Bean
	fun orderServiceV2(logTrace: LogTrace) = run {
		val orderServiceV2 = OrderServiceV2(orderRepositoryV2(logTrace))
		OrderServiceConcreteProxy(orderServiceV2, logTrace)
	}

	@Bean
	fun orderRepositoryV2(logTrace: LogTrace) = run {
		val orderRepositoryV2 = OrderRepositoryV2()
		OrderRepositoryConcreteProxy(orderRepositoryV2, logTrace)
	}
}