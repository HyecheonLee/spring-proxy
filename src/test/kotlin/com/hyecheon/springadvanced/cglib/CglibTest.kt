package com.hyecheon.springadvanced.cglib

import com.hyecheon.springadvanced.cglib.code.TimeMethodInterceptor
import com.hyecheon.springadvanced.common.service.ConcreteService
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.cglib.proxy.Enhancer

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/28
 */
class CglibTest {
	private val log = LoggerFactory.getLogger(this::class.java)

	@DisplayName("1. cglib")
	@Test
	internal fun test_1() {
		val target = ConcreteService()

		val proxy = Enhancer().apply {
			setSuperclass(ConcreteService::class.java)
			setCallback(TimeMethodInterceptor(target))
		}.run { create() as ConcreteService }

		log.info("targetClass={}", target.javaClass)
		log.info("proxyClass={}", proxy.javaClass)

		proxy.call()
	}
}