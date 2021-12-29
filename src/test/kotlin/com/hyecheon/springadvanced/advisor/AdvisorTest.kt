package com.hyecheon.springadvanced.advisor

import com.hyecheon.springadvanced.common.advice.TimeAdvice
import com.hyecheon.springadvanced.common.service.ServiceImpl
import com.hyecheon.springadvanced.common.service.ServiceInterface
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.aop.ClassFilter
import org.springframework.aop.MethodMatcher
import org.springframework.aop.Pointcut
import org.springframework.aop.framework.ProxyFactory
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.aop.support.NameMatchMethodPointcut
import java.lang.reflect.Method

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/30
 */
class AdvisorTest {
	companion object {
		private val log = LoggerFactory.getLogger(this::class.java)
	}

	@DisplayName("1. advisor")
	@Test
	internal fun test_1() {
		val target = ServiceImpl()
		val proxyFactory = ProxyFactory(target).apply {
			addAdvisor(DefaultPointcutAdvisor(Pointcut.TRUE, TimeAdvice()))
		}
		val proxy = proxyFactory.proxy as ServiceInterface

		proxy.save()
		proxy.find()
	}

	@DisplayName("2. 직접 만든 포인트컷")
	@Test
	internal fun test_2() {
		val target = ServiceImpl()
		val proxyFactory = ProxyFactory(target).apply {
			addAdvisor(DefaultPointcutAdvisor(MyPointcut(), TimeAdvice()))
		}
		val proxy = proxyFactory.proxy as ServiceInterface

		proxy.save()
		proxy.find()
	}

	class MyPointcut : Pointcut {
		override fun getClassFilter(): ClassFilter {
			return ClassFilter.TRUE
		}

		override fun getMethodMatcher(): MethodMatcher {
			return MyMethodMatcher()
		}
	}

	class MyMethodMatcher : MethodMatcher {
		private val matchName = "save"

		override fun matches(method: Method, targetClass: Class<*>): Boolean {
			val result = method.name == matchName
			log.info("포인트컷 호출 method={} targetClass={}", method.name, targetClass)
			log.info("포인트컷 결과 result={}", result)
			return result
		}

		override fun matches(method: Method, targetClass: Class<*>, vararg args: Any?): Boolean {
			return false
		}

		override fun isRuntime(): Boolean {
			return false
		}
	}

	@DisplayName("3. 스프링이 제공하는 포인트컷")
	@Test
	internal fun test_3() {
		val target = ServiceImpl()
		val proxyFactory = ProxyFactory(target).apply {
			addAdvisor(
				DefaultPointcutAdvisor(
					NameMatchMethodPointcut().apply { setMappedNames("save") },
					TimeAdvice()
				)
			)
		}
		val proxy = proxyFactory.proxy as ServiceInterface

		proxy.save()
		proxy.find()
	}
}