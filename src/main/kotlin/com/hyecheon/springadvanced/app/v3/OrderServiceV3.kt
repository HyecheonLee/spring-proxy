package com.hyecheon.springadvanced.app.v3

import com.hyecheon.springadvanced.trace.TraceId
import com.hyecheon.springadvanced.trace.hellotrace.HelloTraceV2
import com.hyecheon.springadvanced.trace.logtrace.LogTrace
import org.springframework.stereotype.Service

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/04
 */
@Service
class OrderServiceV3(
	private val orderRepositoryV3: OrderRepositoryV3,
	private val trace: LogTrace

) {
	fun orderItem(itemId: String) = run {
		val status = trace.begin("OrderService.orderItem()")
		try {
			orderRepositoryV3.save(itemId = itemId)
			trace.end(status)
			"ok"
		} catch (e: Exception) {
			trace.exception(status, e)
			throw e
		}
	}
}