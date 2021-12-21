package com.hyecheon.springadvanced.app.logtrace.v4

import com.hyecheon.springadvanced.trace.logtrace.LogTrace
import com.hyecheon.springadvanced.trace.template.AbstractTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/04
 */
@RestController
class OrderControllerV4(
	private val orderServiceV4: OrderServiceV4,
	private val trace: LogTrace
) {

	@GetMapping("/v4/request")
	fun request(itemId: String?) = run {
		val template = object : AbstractTemplate<String>(trace) {
			override fun call(): String {
				return orderServiceV4.orderItem(itemId!!)
			}
		}
		template.execute("OrderController.request()")
	}
}