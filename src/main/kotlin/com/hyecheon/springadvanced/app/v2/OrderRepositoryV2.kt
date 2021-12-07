package com.hyecheon.springadvanced.app.v2

import com.hyecheon.springadvanced.trace.TraceId
import com.hyecheon.springadvanced.trace.hellotrace.HelloTraceV2
import org.springframework.stereotype.Repository
import java.lang.Thread.sleep

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/04
 */
@Repository
class OrderRepositoryV2(
	private val trace: HelloTraceV2
) {
	fun save(traceId: TraceId, itemId: String) = run {
		val status = trace.beginSync(traceId, "OrderRepository.save()")
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