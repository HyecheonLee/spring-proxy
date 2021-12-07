package com.hyecheon.springadvanced.app.v1

import com.hyecheon.springadvanced.trace.hellotrace.HelloTraceV1
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/04
 */
@RestController
class OrderControllerV1(
	private val orderServiceV1: OrderServiceV1,
	private val trace: HelloTraceV1
) {

	@GetMapping("/v1/request")
	fun request(itemId: String?) = run {
		val status = trace.begin("OrderController.request()")
		try {
			orderServiceV1.orderItem(itemId!!)
			trace.end(status)
			"ok"
		} catch (e: Exception) {
			trace.exception(status, e)
			throw e
		}
	}
}