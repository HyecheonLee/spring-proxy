package com.hyecheon.springadvanced.app.v2

import com.hyecheon.springadvanced.trace.hellotrace.HelloTraceV2
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/04
 */
@RestController
class OrderControllerV2(
	private val orderServiceV2: OrderServiceV2,
	private val trace: HelloTraceV2
) {

	@GetMapping("/v2/request")
	fun request(itemId: String?) = run {
		val status = trace.begin("OrderController.request()")
		try {
			orderServiceV2.orderItem(status.traceId, itemId!!)
			trace.end(status)
			"ok"
		} catch (e: Exception) {
			trace.exception(status, e)
			throw e
		}
	}
}