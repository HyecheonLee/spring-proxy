package com.hyecheon.springadvanced.config.v1_proxy.interface_proxy

import com.hyecheon.springadvanced.app.proxy.v1.OrderServiceV1
import com.hyecheon.springadvanced.trace.TraceStatus
import com.hyecheon.springadvanced.trace.logtrace.LogTrace

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/25
 */
class OrderServiceInterfaceProxy(
	private val target: OrderServiceV1,
	private val logTrace: LogTrace
) : OrderServiceV1 {
	override fun orderItem(itemId: String) {
		var status: TraceStatus? = null
		try {
			status = logTrace.begin("OrderService.orderItme()")
			target.orderItem(itemId)
			logTrace.end(status)
		} catch (e: Exception) {
			status?.let { logTrace.exception(status, e) }
			throw e
		}
	}
}