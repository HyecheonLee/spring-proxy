package com.hyecheon.springadvanced.app.logtrace.v1

import com.hyecheon.springadvanced.trace.hellotrace.HelloTraceV1
import org.springframework.stereotype.Service

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/04
 */
@Service
class OrderServiceV1(
	private val orderRepository: OrderRepositoryV1,
	private val trace: HelloTraceV1

) {
	fun orderItem(itemId: String) = run {
		val status = trace.begin("OrderServiceV1.orderItem()")
		try {
			orderRepository.save(itemId = itemId)
			trace.end(status)
			"ok"
		} catch (e: Exception) {
			trace.exception(status, e)
			throw e
		}
	}
}