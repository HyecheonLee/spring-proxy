package com.hyecheon.springadvanced.decorator.code

import org.slf4j.LoggerFactory

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/25
 */
class TimeDecorator(
	private val component: Component
) : Component {
	private val log = LoggerFactory.getLogger(this::class.java)
	override fun operation(): String {
		log.info("TimeDecorator 실행")
		val startTime = System.currentTimeMillis()
		val result = component.operation()
		val endTime = System.currentTimeMillis()
		val resultTime = endTime - startTime
		log.info("TimeDecorator 종료 resultTime={}ms", resultTime)
		return result
	}
}