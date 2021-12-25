package com.hyecheon.springadvanced.app.proxy.v2

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/22
 */
open class OrderServiceV2(
	private val orderRepository: OrderRepositoryV2?
) {
	open fun orderItem(itemId: String) {
		orderRepository?.save(itemId)
	}
}