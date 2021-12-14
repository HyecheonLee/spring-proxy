package com.hyecheon.springadvanced.trace.template.code

import org.slf4j.LoggerFactory

class SubClassLogic1 : AbstractTemplate() {
	private val log = LoggerFactory.getLogger(this::class.java)
	override fun call() {
		log.info("비즈니스 로직1 실행")
	}
}