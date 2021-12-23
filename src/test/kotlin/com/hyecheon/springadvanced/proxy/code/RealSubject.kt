package com.hyecheon.springadvanced.proxy.code

import org.slf4j.LoggerFactory
import java.lang.Thread.sleep

class RealSubject : Subject {
	private val log = LoggerFactory.getLogger(this::class.java)
	override fun operation(): String {
		log.info("실제 객체 호출")
		sleep(1000)
		return "data"
	}
}