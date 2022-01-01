package com.hyecheon.springadvanced.app.proxy.v3

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/22
 */
@RestController
class OrderControllerV3(
	private val orderService: OrderServiceV3
) {

	@GetMapping("/proxy/v3/request")
	fun request(itemId: String): String {
		orderService.orderItem(itemId)
		return "ok"
	}

	@GetMapping("/proxy/v3/no-log")
	fun noLog(): String {
		return "ok"
	}
}