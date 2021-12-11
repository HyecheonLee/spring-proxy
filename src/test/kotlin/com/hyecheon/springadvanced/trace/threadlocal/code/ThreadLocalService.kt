package com.hyecheon.springadvanced.trace.threadlocal.code

import org.slf4j.LoggerFactory
import java.lang.Thread.sleep

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/11
 */
class ThreadLocalService {
	private val log = LoggerFactory.getLogger(this::class.java)

	private var nameStore: ThreadLocal<String> = ThreadLocal()

	fun logic(name: String) = run {
		log.info("저장 name={} --> nameStore={}", name, nameStore.get())
		nameStore.set(name)
		sleep(1000)
		log.info("조회 nameStore={}", nameStore.get())
		nameStore.get()
	}
}