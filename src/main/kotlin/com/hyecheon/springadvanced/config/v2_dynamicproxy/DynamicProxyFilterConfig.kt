package com.hyecheon.springadvanced.config.v2_dynamicproxy

import com.hyecheon.springadvanced.app.proxy.v1.*
import com.hyecheon.springadvanced.config.v2_dynamicproxy.handler.LogTraceBasicHandler
import com.hyecheon.springadvanced.config.v2_dynamicproxy.handler.LogTraceFilterHandler
import com.hyecheon.springadvanced.trace.logtrace.LogTrace
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.lang.reflect.Proxy

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/28
 */
@Configuration
class DynamicProxyFilterConfig {
	companion object {
		val PATTERNS = arrayOf("request*", "order*", "service*")
	}

	@Bean
	fun orderControllerV1(logTrace: LogTrace) = run {
		val orderController = OrderControllerV1Impl(orderServiceV1(logTrace))
		val proxy = Proxy.newProxyInstance(
			OrderControllerV1::class.java.classLoader,
			arrayOf(OrderControllerV1::class.java),
			LogTraceFilterHandler(orderController, logTrace, PATTERNS)
		) as OrderControllerV1
		proxy
	}

	@Bean
	fun orderServiceV1(logTrace: LogTrace) = run {
		val orderService = OrderServiceV1Impl(orderRepositoryV1(logTrace))
		val proxy = Proxy.newProxyInstance(
			OrderServiceV1::class.java.classLoader,
			arrayOf(OrderServiceV1::class.java),
			LogTraceFilterHandler(orderService, logTrace, PATTERNS)
		) as OrderServiceV1
		proxy
	}

	@Bean
	fun orderRepositoryV1(logTrace: LogTrace) = run {
		val orderRepositoryV1Impl = OrderRepositoryV1Impl()

		val proxy = Proxy.newProxyInstance(
			OrderRepositoryV1::class.java.classLoader,
			arrayOf(OrderRepositoryV1::class.java),
			LogTraceFilterHandler(orderRepositoryV1Impl, logTrace, PATTERNS)
		) as OrderRepositoryV1
		proxy
	}
}