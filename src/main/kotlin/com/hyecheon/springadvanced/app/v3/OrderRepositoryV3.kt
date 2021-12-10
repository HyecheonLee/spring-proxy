package com.hyecheon.springadvanced.app.v3

import com.hyecheon.springadvanced.trace.TraceId
import com.hyecheon.springadvanced.trace.hellotrace.HelloTraceV2
import com.hyecheon.springadvanced.trace.logtrace.LogTrace
import org.springframework.beans.factory.ObjectFactory
import org.springframework.stereotype.Repository
import java.lang.Thread.sleep

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/04
 */
@Repository
class OrderRepositoryV3(
	private val traceObject: ObjectFactory<LogTrace>
) {
	fun save(itemId: String) = run {
		val trace = traceObject.`object`
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