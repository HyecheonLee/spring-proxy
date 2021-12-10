package com.hyecheon.springadvanced.trace.logtrace

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/10
 */
class FieldLogTraceTest {
	val trace = FieldLogTrace()


	@DisplayName("1. begin_end_level2")
	@Test
	internal fun test_1() {
		val status1 = trace.begin("hello1")
		val status2 = trace.begin("hello2")

		trace.end(status2)

		trace.end(status1)
	}

	@DisplayName("2. begin exception level2")
	@Test
	internal fun test_2() {
		val status1 = trace.begin("hello1")
		val status2 = trace.begin("hello2")

		trace.exception(status2, IllegalArgumentException())
		trace.exception(status1, IllegalArgumentException())
	}
}