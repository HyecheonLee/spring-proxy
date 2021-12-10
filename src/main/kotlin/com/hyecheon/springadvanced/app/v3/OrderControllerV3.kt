package com.hyecheon.springadvanced.app.v3

import com.hyecheon.springadvanced.trace.logtrace.LogTrace
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.ObjectFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/04
 */
@RestController
class OrderControllerV3(
	private val orderServiceV3: OrderServiceV3,
	private val traceObject: ObjectFactory<LogTrace>
) {

	@GetMapping("/v3/request")
	fun request(itemId: String?) = run {
		val trace = traceObject.`object`
		val status = trace.begin("OrderController.request()")
		try {
			orderServiceV3.orderItem(itemId!!)
			trace.end(status)
			"ok"
		} catch (e: Exception) {
			trace.exception(status, e)
			throw e
		}
	}
}