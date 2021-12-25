package com.hyecheon.springadvanced.decorator.code

import org.slf4j.LoggerFactory

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/25
 */
class MessageDecorator(
	private val component: Component
) : Component {
	private val log = LoggerFactory.getLogger(this::class.java)
	override fun operation(): String {
		log.info("MessageDecorator 실행")
		val operation = component.operation()
		val decoResult = "****${operation}****"
		log.info("MessageDecorator 꾸미기 적용 전={}, 적용 후={}", operation, decoResult)
		return decoResult
	}
}