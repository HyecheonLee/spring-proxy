package com.hyecheon.springadvanced.config.v3_proxyfactory

import com.hyecheon.springadvanced.app.proxy.v1.*
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
class ProxyFactoryConfigV1 {


	@Bean
	fun getAdvisor(logTarget: LogTrace) = run {
		DefaultPointcutAdvisor(
			NameMatchMethodPointcut().apply { setMappedNames("request*", "order*", "save*") },
			LogTraceAdvice(logTarget)
		)
	}

	@Bean
	fun orderControllerV1(logTrace: LogTrace) = run {
		val orderController = OrderControllerV1Impl(orderServiceV1(logTrace))
		ProxyFactory(orderController).apply { addAdvisor(getAdvisor(logTrace)) }.run { proxy as OrderControllerV1 }
	}

	@Bean
	fun orderServiceV1(logTrace: LogTrace) = run {
		val orderService = OrderServiceV1Impl(orderRepositoryV1(logTrace))
		ProxyFactory(orderService).apply { addAdvisor(getAdvisor(logTrace)) }.run { proxy as OrderServiceV1 }
	}

	@Bean
	fun orderRepositoryV1(logTrace: LogTrace) = run {
		val orderRepository = OrderRepositoryV1Impl()
		ProxyFactory(orderRepository).apply { addAdvisor(getAdvisor(logTrace)) }.run { proxy as OrderRepositoryV1 }
	}
}