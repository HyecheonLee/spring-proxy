package com.hyecheon.springadvanced.proxyfactory

import com.hyecheon.springadvanced.common.advice.TimeAdvice
import com.hyecheon.springadvanced.common.service.ConcreteService
import com.hyecheon.springadvanced.common.service.ServiceImpl
import com.hyecheon.springadvanced.common.service.ServiceInterface
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.aop.framework.ProxyFactory
import org.springframework.aop.support.AopUtils

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/28
 */
class ProxyFactoryTest {
	private val log = LoggerFactory.getLogger(this::class.java)

	@DisplayName("1. 인터페이스가 있으면 jdk 동적 프록시 사용")
	@Test
	internal fun test_1() {
		val target = ServiceImpl()
		val proxy = ProxyFactory(target)
			.apply { addAdvice(TimeAdvice()) }
			.run { proxy as ServiceInterface }

		log.info("targetClass={}", target.javaClass)
		log.info("proxyClass={}", proxy.javaClass)

		proxy.save()

		Assertions.assertThat(AopUtils.isAopProxy(proxy)).isTrue
		Assertions.assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue
		Assertions.assertThat(AopUtils.isCglibProxy(proxy)).isFalse
	}

	@DisplayName("2. 구체 클래스만 있으면 CGLIB 사용")
	@Test
	internal fun test_2() {
		val target = ConcreteService()
		val proxy = ProxyFactory(target)
			.apply { addAdvice(TimeAdvice()) }
			.run { proxy as ConcreteService }

		log.info("targetClass={}", target.javaClass)
		log.info("proxyClass={}", proxy.javaClass)

		proxy.call()

		Assertions.assertThat(AopUtils.isAopProxy(proxy)).isTrue
		Assertions.assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse
		Assertions.assertThat(AopUtils.isCglibProxy(proxy)).isTrue
	}

	@DisplayName("3. ProxyTargetClass 옵션을 사용하면 인터페이스가 있어도 CGLIB를 사용하고,  클래스 기반 프록시 사용")
	@Test
	fun test_3() {
		val target = ServiceImpl()
		val proxy = ProxyFactory(target)
			.apply {
				isProxyTargetClass = true
				addAdvice(TimeAdvice())
			}
			.run { proxy as ServiceInterface }

		log.info("targetClass={}", target.javaClass)
		log.info("proxyClass={}", proxy.javaClass)

		proxy.save()

		Assertions.assertThat(AopUtils.isAopProxy(proxy)).isTrue
		Assertions.assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse
		Assertions.assertThat(AopUtils.isCglibProxy(proxy)).isTrue
	}
}