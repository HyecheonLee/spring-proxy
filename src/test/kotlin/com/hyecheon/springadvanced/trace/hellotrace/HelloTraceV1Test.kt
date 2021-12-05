package com.hyecheon.springadvanced.trace.hellotrace

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import java.lang.IllegalStateException

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/05
 */
class HelloTraceV1Test {

	@DisplayName("1. begin end")
	@Test
	fun test_1() {
		val traceV1 = HelloTraceV1()
		val status = traceV1.begin("hello")
		traceV1.end(status)
	}

	@DisplayName("2. begin exception")
	@Test
	internal fun test_2() {
		val traceV1 = HelloTraceV1()
		val status = traceV1.begin("hello")
		traceV1.exception(status, IllegalStateException())
	}
}