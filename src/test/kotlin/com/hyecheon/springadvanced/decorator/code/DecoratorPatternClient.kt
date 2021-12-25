package com.hyecheon.springadvanced.decorator.code

import org.slf4j.LoggerFactory

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/25
 */
class DecoratorPatternClient(
	private val component: Component
) {
	private val log = LoggerFactory.getLogger(this::class.java)
	fun execute() = run {
		val result = component.operation()
		log.info("result={}", result)
	}
}