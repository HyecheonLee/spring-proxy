package com.hyecheon.springadvanced.app.logtrace.v0

import org.springframework.stereotype.Service

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/04
 */
@Service
class OrderServiceV0(
	private val orderRepositoryV0: OrderRepositoryV0
) {
	fun orderItem(itemId: String) = run {
		orderRepositoryV0.save(itemId = itemId)
	}
}