package com.hyecheon.springadvanced.config.v3_proxyfactory

import com.hyecheon.springadvanced.app.proxy.v1.*
import com.hyecheon.springadvanced.app.proxy.v2.OrderControllerV2
import com.hyecheon.springadvanced.app.proxy.v2.OrderRepositoryV2
import com.hyecheon.springadvanced.app.proxy.v2.OrderServiceV2
import com.hyecheon.springadvanced.config.v3_proxyfactory.advice.LogTraceAdvice
import com.hyecheon.springadvanced.trace.logtrace.LogTrace
import org.springframework.aop.framework.ProxyFactory
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.aop.support.NameMatchMethodPointcut
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/30
 */
@Configuration
class ProxyFactoryConfigV2 {


	@Bean
	fun getAdvisor(logTarget: LogTrace) = run {
		DefaultPointcutAdvisor(
			NameMatchMethodPointcut().apply { setMappedNames("request*", "order*", "save*") },
			LogTraceAdvice(logTarget)
		)
	}

	@Bean
	fun orderControllerV2(logTrace: LogTrace) = run {
		val orderController = OrderControllerV2(orderServiceV2(logTrace))
		ProxyFactory(orderController).apply { addAdvisor(getAdvisor(logTrace)) }.run { proxy as OrderControllerV2 }
	}

	@Bean
	fun orderServiceV2(logTrace: LogTrace) = run {
		val orderService = OrderServiceV2(orderRepositoryV2(logTrace))
		ProxyFactory(orderService).apply { addAdvisor(getAdvisor(logTrace)) }.run { proxy as OrderServiceV2 }
	}

	@Bean
	fun orderRepositoryV2(logTrace: LogTrace) = run {
		val orderRepository = OrderRepositoryV2()
		ProxyFactory(orderRepository).apply { addAdvisor(getAdvisor(logTrace)) }.run { proxy as OrderRepositoryV2 }
	}
}