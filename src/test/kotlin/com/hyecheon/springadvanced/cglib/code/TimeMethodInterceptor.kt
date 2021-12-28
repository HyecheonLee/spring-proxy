package com.hyecheon.springadvanced.cglib.code

import org.slf4j.LoggerFactory
import org.springframework.cglib.proxy.MethodInterceptor
import org.springframework.cglib.proxy.MethodProxy
import java.lang.reflect.Method

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/28
 */
class TimeMethodInterceptor(
	private val target: Any
) : MethodInterceptor {
	private val log = LoggerFactory.getLogger(this::class.java)

	override fun intercept(obj: Any, method: Method, args: Array<out Any>?, methodProxy: MethodProxy): Any {
		log.info("TimeProxy 실행")
		val startTime = System.currentTimeMillis()

		val result = methodProxy.invoke(target, args ?: arrayOfNulls<Any>(0))

		val endTime = System.currentTimeMillis()
		val resultTime = endTime - startTime
		log.info("TimeProxy 종료 resultTime={}", resultTime)

		return result
	}
}