package com.hyecheon.springadvanced.app.v1

import com.hyecheon.springadvanced.trace.hellotrace.HelloTraceV1
import org.springframework.stereotype.Repository
import java.lang.Thread.sleep

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/04
 */
@Repository
class OrderRepositoryV1(
	private val trace: HelloTraceV1
) {
	fun save(itemId: String) = run {
		val status = trace.begin("OrderRepository.save()")
		try {
			if (itemId == "ex") {
				throw IllegalStateException("예외 발생")
			}
			sleep(1000)
			trace.end(status)
			"ok"
		} catch (e: Exception) {
			trace.exception(status, e)
			throw e
		}

	}
}