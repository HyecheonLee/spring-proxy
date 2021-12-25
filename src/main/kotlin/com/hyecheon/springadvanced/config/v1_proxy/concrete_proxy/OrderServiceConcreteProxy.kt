package com.hyecheon.springadvanced.config.v1_proxy.concrete_proxy

import com.hyecheon.springadvanced.app.proxy.v2.OrderRepositoryV2
import com.hyecheon.springadvanced.app.proxy.v2.OrderServiceV2
import com.hyecheon.springadvanced.trace.TraceStatus
import com.hyecheon.springadvanced.trace.logtrace.LogTrace

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/25
 */
class OrderServiceConcreteProxy(
	private val target: OrderServiceV2,
	private val logTrace: LogTrace,
) : OrderServiceV2(null) {

	override fun orderItem(itemId: String) = run {
		var status: TraceStatus? = null
		try {
			status = logTrace.begin("OrderService.orderItem()")
			target.orderItem(itemId)
			logTrace.end(status)
		} catch (e: Exception) {
			status?.let {
				logTrace.exception(status, e)
			}
			throw e
		}
	}
}