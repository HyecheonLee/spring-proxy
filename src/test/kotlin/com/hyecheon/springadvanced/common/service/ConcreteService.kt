package com.hyecheon.springadvanced.common.service

import org.slf4j.LoggerFactory

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/28
 */
open class ConcreteService {
	private val log = LoggerFactory.getLogger(this::class.java)
	fun call() = run {
		log.info("Concrete call 호출")
	}
}