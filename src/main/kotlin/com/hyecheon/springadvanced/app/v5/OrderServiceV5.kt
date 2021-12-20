package com.hyecheon.springadvanced.app.v5

import com.hyecheon.springadvanced.trace.callback.TraceTemplate
import com.hyecheon.springadvanced.trace.logtrace.LogTrace
import com.hyecheon.springadvanced.trace.template.AbstractTemplate
import org.springframework.stereotype.Service

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/04
 */
@Service
class OrderServiceV5(
	private val orderRepositoryV5: OrderRepositoryV5,
	private val trace: TraceTemplate

) {
	fun orderItem(itemId: String) = run {
		trace.execute("OrderService.orderItem()") { orderRepositoryV5.save(itemId = itemId) }
	}
}