package com.hyecheon.springadvanced.jdkdynamic

import com.hyecheon.springadvanced.jdkdynamic.code.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import java.lang.reflect.Proxy

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/27
 */
class JdkDynamicProxyTest {
	private val log = LoggerFactory.getLogger(this::class.java)

	@DisplayName("1. dynamicA")
	@Test
	internal fun test_1() {
		val target = AImpl()
		val handler = TimeInvocationHandler(target)

		val proxy =
			Proxy.newProxyInstance(
				AInterface::class.java.classLoader,
				arrayOf(AInterface::class.java),
				handler
			) as AInterface

		proxy.call()
		log.info("targetClass={}", target.javaClass)
		log.info("proxyClass={}", proxy.javaClass)
	}

	@DisplayName("2. dynamicB")
	@Test
	internal fun test_2() {
		val target = BImpl()
		val handler = TimeInvocationHandler(target)

		val proxy =
			Proxy.newProxyInstance(
				BInterface::class.java.classLoader,
				arrayOf(BInterface::class.java),
				handler
			) as BInterface

		proxy.call()
		log.info("targetClass={}", target.javaClass)
		log.info("proxyClass={}", proxy.javaClass)
	}

}