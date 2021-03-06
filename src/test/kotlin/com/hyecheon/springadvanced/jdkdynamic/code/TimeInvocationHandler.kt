package com.hyecheon.springadvanced.jdkdynamic.code

import org.slf4j.LoggerFactory
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/27
 */
class TimeInvocationHandler(
	private val target: Any
) : InvocationHandler {
	private val log = LoggerFactory.getLogger(this::class.java)
	override fun invoke(proxy: Any, method: Method, args: Array<out Any>?): Any? {
		log.info("TimeProxy 실행")
		val startTime = System.currentTimeMillis()

		val result = if (args != null) method.invoke(target, *args)
		else method.invoke(target)


		val endTime = System.currentTimeMillis()

		val resultTime = endTime - startTime
		log.info("TimeProxy 종료 resultTime={}", resultTime)

		return result
	}

}