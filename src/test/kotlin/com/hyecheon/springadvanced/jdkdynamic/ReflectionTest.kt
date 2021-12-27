package com.hyecheon.springadvanced.jdkdynamic

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import java.lang.reflect.Method

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/27
 */
class ReflectionTest {
	private val log = LoggerFactory.getLogger(this::class.java)

	@DisplayName("1. reflection")
	@Test
	internal fun test_1() {
		val target = Hello()

		//공통 로직1 시작
		log.info("start")
		val result1 = target.callA()
		log.info("result={}", result1)
		//공통 로직1 종료

		//공통 로직2 시작
		log.info("start")
		val result2 = target.callB()
		log.info("result={}", result2)
		//공통 로직2 종료
	}


	@DisplayName("2. reflection-1")
	@Test
	internal fun test_2() {
		val classHello = Class.forName("com.hyecheon.springadvanced.jdkdynamic.ReflectionTest\$Hello")

		val target = Hello()

		//공통 로직1 시작
		log.info("start")
		val methodCallA = classHello.getMethod("callA")
		val result1 = methodCallA.invoke(target)
		log.info("result={}", result1)
		//공통 로직1 종료

		//공통 로직2 시작
		log.info("start")
		val methodCallB = classHello.getMethod("callB")
		val result2 = methodCallB.invoke(target)
		log.info("result={}", result2)
		//공통 로직2 종료

	}


	@DisplayName("3. reflection-2")
	@Test
	internal fun test_3() {
		val classHello = Class.forName("com.hyecheon.springadvanced.jdkdynamic.ReflectionTest\$Hello")

		val target = Hello()

		dynamicCall(classHello.getMethod("callA"), target)

		dynamicCall(classHello.getMethod("callB"), target)
	}

	private fun dynamicCall(method: Method, target: Any) = run {
		log.info("start")
		val result = method.invoke(target)
		log.info("result={}", result)
		result
	}


	class Hello {
		private val log = LoggerFactory.getLogger(this::class.java)
		fun callA() = run {
			log.info("callA")
			"A"
		}

		fun callB() = run {
			log.info("callB")
			"B"
		}
	}
}