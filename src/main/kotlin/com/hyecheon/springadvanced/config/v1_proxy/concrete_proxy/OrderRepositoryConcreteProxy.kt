package com.hyecheon.springadvanced.config.v1_proxy.concrete_proxy

import com.hyecheon.springadvanced.app.proxy.v2.OrderRepositoryV2
import com.hyecheon.springadvanced.trace.TraceStatus
import com.hyecheon.springadvanced.trace.logtrace.LogTrace

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/25
 */
class OrderRepositoryConcreteProxy(
	private val target: OrderRepositoryV2,
	private val logTrace: LogTrace
) : OrderRepositoryV2() {

	override fun save(itemId: String) = run {
		var status: TraceStatus? = null
		try {
			status = logTrace.begin("OrderRepository.request()")
			target.save(itemId)
			logTrace.end(status)
		} catch (e: Exception) {
			status?.let {
				logTrace.exception(status, e)
			}
			throw e
		}
	}
}