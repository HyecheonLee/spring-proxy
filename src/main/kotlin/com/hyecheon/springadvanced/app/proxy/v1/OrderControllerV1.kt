package com.hyecheon.springadvanced.app.proxy.v1

import org.springframework.web.bind.annotation.*

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/21
 */

@RequestMapping
@ResponseBody
interface OrderControllerV1 {

	@GetMapping("/proxy/v1/request")
	fun request(@RequestParam("itemId") itemId: String): String

	@GetMapping("/proxy/v1/no-log")
	fun noLog()
}