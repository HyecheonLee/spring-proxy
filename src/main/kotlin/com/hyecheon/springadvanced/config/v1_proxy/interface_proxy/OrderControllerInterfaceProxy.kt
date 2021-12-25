package com.hyecheon.springadvanced.config.v1_proxy.interface_proxy

import com.hyecheon.springadvanced.app.proxy.v1.OrderControllerV1
import com.hyecheon.springadvanced.trace.TraceStatus
import com.hyecheon.springadvanced.trace.logtrace.LogTrace

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/25
 */
class OrderControllerInterfaceProxy(
	private val target: OrderControllerV1,
	private val logTrace: LogTrace
) : OrderControllerV1 {
	override fun request(itemId: String): String {
		var status: TraceStatus? = null
		try {
			status = logTrace.begin("OrderController.request()")
			val result = target.request(itemId)
			logTrace.end(status)
			return result
		} catch (e: Exception) {
			status?.let { logTrace.exception(status, e) }
			throw e
		}
	}

	override fun noLog() {
		target.noLog()
	}
}