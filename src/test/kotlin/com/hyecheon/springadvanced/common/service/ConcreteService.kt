package com.hyecheon.springadvanced.common.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/28
 */
open class ConcreteService {
	private val log: Logger = LoggerFactory.getLogger(this::class.java)
	open fun call() = run {
		log.info("ConcreteService 호출")
	}
}