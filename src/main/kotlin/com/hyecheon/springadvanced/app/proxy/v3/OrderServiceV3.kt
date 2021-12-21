package com.hyecheon.springadvanced.app.proxy.v3

import org.springframework.stereotype.Service

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/22
 */
@Service
class OrderServiceV3(
	private val orderRepository: OrderRepositoryV3
) {
	fun orderItem(itemId: String) {
		orderRepository.save(itemId)
	}
}