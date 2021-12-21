package com.hyecheon.springadvanced.app.logtrace.v4

import com.hyecheon.springadvanced.trace.logtrace.LogTrace
import com.hyecheon.springadvanced.trace.template.AbstractTemplate
import org.springframework.stereotype.Service

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/04
 */
@Service
class OrderServiceV4(
	private val orderRepositoryV4: OrderRepositoryV4,
	private val trace: LogTrace

) {
	fun orderItem(itemId: String) = run {
		val template = object : AbstractTemplate<Unit>(trace) {
			override fun call() {
				orderRepositoryV4.save(itemId = itemId)
			}
		}
		template.execute("OrderService.orderItem()")
		"OK"
	}
}