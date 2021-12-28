package com.hyecheon.springadvanced.common.advice

import org.aopalliance.intercept.MethodInterceptor
import org.aopalliance.intercept.MethodInvocation
import org.slf4j.LoggerFactory

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/28
 */
class TimeAdvice : MethodInterceptor {
	private val log = LoggerFactory.getLogger(this::class.java)
	override fun invoke(invocation: MethodInvocation): Any? {
		log.info("TimeProxy 실행")
		val startTime = System.currentTimeMillis()

		val result = invocation.proceed()

		val endTime = System.currentTimeMillis()
		val resultTime = endTime - startTime
		log.info("TimeProxy 종료 resultTime={}", resultTime)

		return result
	}
}