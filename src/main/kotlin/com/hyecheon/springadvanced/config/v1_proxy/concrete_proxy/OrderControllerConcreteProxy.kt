package com.hyecheon.springadvanced.config.v1_proxy.concrete_proxy

import com.hyecheon.springadvanced.app.proxy.v2.OrderControllerV2
import com.hyecheon.springadvanced.trace.TraceStatus
import com.hyecheon.springadvanced.trace.logtrace.LogTrace

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/25
 */
class OrderControllerConcreteProxy(
	private val target: OrderControllerV2,
	private val logTrace: LogTrace
) : OrderControllerV2(null) {

	override fun request(itemId: String) = run {
		var status: TraceStatus? = null
		try {
			status = logTrace.begin("OrderController.request()")
			target.request(itemId)
			logTrace.end(status)
			"ok"
		} catch (e: Exception) {
			status?.let {
				logTrace.exception(status, e)
			}
			throw e
		}
	}

	override fun noLog() = run {
		target.noLog()
	}
}