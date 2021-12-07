package com.hyecheon.springadvanced.app.v2

import com.hyecheon.springadvanced.trace.TraceId
import com.hyecheon.springadvanced.trace.hellotrace.HelloTraceV2
import org.springframework.stereotype.Service

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/04
 */
@Service
class OrderServiceV2(
	private val orderRepositoryV2: OrderRepositoryV2,
	private val trace: HelloTraceV2

) {
	fun orderItem(traceId: TraceId, itemId: String) = run {
		val status = trace.beginSync(traceId, "OrderService.orderItem()")
		try {
			orderRepositoryV2.save(status.traceId, itemId = itemId)
			trace.end(status)
			"ok"
		} catch (e: Exception) {
			trace.exception(status, e)
			throw e
		}
	}
}