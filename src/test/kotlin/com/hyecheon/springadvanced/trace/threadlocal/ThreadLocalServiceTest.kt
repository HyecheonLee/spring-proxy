package com.hyecheon.springadvanced.trace.threadlocal

import com.hyecheon.springadvanced.trace.threadlocal.code.FieldService
import com.hyecheon.springadvanced.trace.threadlocal.code.ThreadLocalService
import kotlinx.coroutines.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import java.lang.Thread.sleep

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/11
 */
class ThreadLocalServiceTest {
	private val log = LoggerFactory.getLogger(this::class.java)
	private val fieldService = ThreadLocalService()

	@DisplayName("1. field")
	@Test
	fun test_1() = runBlocking {
		log.info("main start")
		val job = CoroutineScope(Dispatchers.Default).launch {
			launch(Dispatchers.Default + CoroutineName("userA")) { fieldService.logic("userA") }
			launch(Dispatchers.Default + CoroutineName("userB")) { fieldService.logic("userB") }
		}
		job.join()
		log.info("main exit")
	}
}