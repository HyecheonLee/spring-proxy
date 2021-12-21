package com.hyecheon.springadvanced.app.logtrace.v0

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/04
 */
@RestController
class OrderControllerV0(
	private val orderServiceV0: OrderServiceV0
) {

	@GetMapping("/v0/request")
	fun request(itemId: String) = run {
		orderServiceV0.orderItem(itemId)
		"ok"
	}

}