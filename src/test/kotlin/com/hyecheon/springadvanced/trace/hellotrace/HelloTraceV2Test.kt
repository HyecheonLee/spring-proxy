package com.hyecheon.springadvanced.trace.hellotrace

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.lang.IllegalStateException

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/08
 */
class HelloTraceV2Test {
	@DisplayName("1. begin end")
	@Test
	fun test_1() {
		val traceV2 = HelloTraceV2()
		val status = traceV2.begin("hello")
		val status2 = traceV2.beginSync(status.traceId, "hello2")
		traceV2.end(status2)
		traceV2.end(status)
	}

	@DisplayName("2. begin exception")
	@Test
	internal fun test_2() {
		val traceV2 = HelloTraceV2()
		val status1 = traceV2.begin("hello")
		val status2 = traceV2.beginSync(status1.traceId, "hello")
		traceV2.exception(status2, IllegalStateException())
		traceV2.exception(status1, IllegalStateException())
	}
}