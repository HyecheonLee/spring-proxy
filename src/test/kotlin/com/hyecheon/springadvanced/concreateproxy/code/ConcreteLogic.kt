package com.hyecheon.springadvanced.concreateproxy.code

import org.slf4j.LoggerFactory

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/25
 */
open class ConcreteLogic {
	private val log = LoggerFactory.getLogger(this::class.java)

	open fun operation() = run {
		log.info("ConcreteLogin 실행")
		"data"
	}
}