package com.hyecheon.springadvanced.trace.template.code

import org.slf4j.LoggerFactory

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/15
 */
abstract class AbstractTemplate {
	private val log = LoggerFactory.getLogger(this::class.java)

	fun execute() = run {
		val startTime = System.currentTimeMillis()
		//비즈니스 로직 실행
		call()
		//비즈니스 로직 종료
		val endTime = System.currentTimeMillis()
		val resultTime = endTime - startTime
		log.info("resultTime={}", resultTime)
	}

	protected abstract fun call()
}