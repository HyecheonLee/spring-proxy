package com.hyecheon.springadvanced.trace.threadlocal

import com.hyecheon.springadvanced.trace.threadlocal.code.FieldService
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
class FieldServiceTest {
	private val log = LoggerFactory.getLogger(this::class.java)
	private val fieldService = FieldService()

	@DisplayName("1. field")
	@Test
	fun test_1() {
		runBlocking {
			log.info("main start")
			val job = launch(Dispatchers.Default) { fieldService.logic("userA") }
			val job2 = launch(Dispatchers.Default) { fieldService.logic("userB") }

			log.info("main exit")
			job.join()
			job2.join()
		}
	}
}