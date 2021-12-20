package com.hyecheon.springadvanced.app.v5

import com.hyecheon.springadvanced.trace.callback.TraceTemplate
import com.hyecheon.springadvanced.trace.logtrace.LogTrace
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/04
 */
@RestController
class OrderControllerV5(
	private val orderServiceV5: OrderServiceV5,
	logTrace: LogTrace
) {
	val trace = TraceTemplate(logTrace)


	@GetMapping("/v5/request")
	fun request(itemId: String?) = run {
		trace.execute("OrderController.request()") {
			orderServiceV5.orderItem(itemId!!)
			"ok"
		}
	}
}