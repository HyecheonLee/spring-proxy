package com.hyecheon.springadvanced.advisor

import com.hyecheon.springadvanced.common.advice.TimeAdvice
import com.hyecheon.springadvanced.common.service.ServiceImpl
import com.hyecheon.springadvanced.common.service.ServiceInterface
import org.aopalliance.intercept.MethodInterceptor
import org.aopalliance.intercept.MethodInvocation
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.aop.Pointcut
import org.springframework.aop.framework.ProxyFactory
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.aop.support.NameMatchMethodPointcut

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/30
 */
class MultiAdvisorTest {
	companion object {
		private val log = LoggerFactory.getLogger(this::class.java)
	}

	@DisplayName("1. 여러 프록시")
	@Test
	internal fun test_1() {
		val target = ServiceImpl()
		val proxyFactory = ProxyFactory(target)
			.apply { addAdvisor(DefaultPointcutAdvisor(Pointcut.TRUE, Advice1())) }
		val proxy =
			ProxyFactory(proxyFactory.proxy)
				.apply { addAdvisor(DefaultPointcutAdvisor(Pointcut.TRUE, Advice2())) }
				.run { proxy as ServiceInterface }

		proxy.save()
	}

	@DisplayName("2. 하나의 프록시, 여러 어드바이져")
	@Test
	internal fun test_2() {
		val target = ServiceImpl()
		val proxy = ProxyFactory(target)
			.apply {
				addAdvisor(DefaultPointcutAdvisor(Pointcut.TRUE, Advice2()))
				addAdvisor(DefaultPointcutAdvisor(Pointcut.TRUE, Advice1()))
			}
			.run { proxy as ServiceInterface }

		proxy.save()
	}


	class Advice1 : MethodInterceptor {
		override fun invoke(invocation: MethodInvocation): Any? {
			log.info("advice1 호출")
			return invocation.proceed()
		}
	}

	class Advice2 : MethodInterceptor {
		override fun invoke(invocation: MethodInvocation): Any? {
			log.info("advice2 호출")
			return invocation.proceed()
		}
	}
}