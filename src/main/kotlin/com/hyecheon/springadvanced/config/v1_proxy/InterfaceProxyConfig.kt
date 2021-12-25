package com.hyecheon.springadvanced.config.v1_proxy

import com.hyecheon.springadvanced.app.proxy.v1.OrderControllerV1Impl
import com.hyecheon.springadvanced.app.proxy.v1.OrderRepositoryV1Impl
import com.hyecheon.springadvanced.app.proxy.v1.OrderServiceV1Impl
import com.hyecheon.springadvanced.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy
import com.hyecheon.springadvanced.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy
import com.hyecheon.springadvanced.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy
import com.hyecheon.springadvanced.trace.logtrace.LogTrace
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/25
 */
@Configuration
class InterfaceProxyConfig {

	@Bean
	fun orderControllerV1(logTrace: LogTrace) = run {
		val controllerImpl = OrderControllerV1Impl(orderServiceV1(logTrace))
		OrderControllerInterfaceProxy(controllerImpl, logTrace)
	}

	@Bean
	fun orderServiceV1(logTrace: LogTrace) = run {
		val serviceV1Impl = OrderServiceV1Impl(orderRepositoryV1(logTrace))
		OrderServiceInterfaceProxy(serviceV1Impl, logTrace)
	}

	@Bean
	fun orderRepositoryV1(logTrace: LogTrace) = run {
		val target = OrderRepositoryV1Impl()
		OrderRepositoryInterfaceProxy(target, logTrace)
	}
}