package com.hyecheon.springadvanced.app.proxy.v2

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/22
 */
@RequestMapping
@ResponseBody
open class OrderControllerV2(
	private val orderService: OrderServiceV2?
) {

	@GetMapping("/proxy/v2/request")
	open fun request(itemId: String): String {
		orderService?.orderItem(itemId)
		return "ok"
	}

	@GetMapping("/proxy/v2/no-log")
	open fun noLog(): String {
		return "ok"
	}
}