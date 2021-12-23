package com.hyecheon.springadvanced.proxy.code

import org.slf4j.LoggerFactory

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/23
 */
class CacheProxy(
	private val target: Subject
) : Subject {
	private val log = LoggerFactory.getLogger(this::class.java)
	private var cacheValue: String? = null

	override fun operation(): String {
		log.info("프록시 호출")
		if (cacheValue == null) {
			cacheValue = target.operation()
		}
		return cacheValue ?: ""
	}
}