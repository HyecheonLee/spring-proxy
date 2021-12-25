package com.hyecheon.springadvanced.config.v1_proxy.interface_proxy

import com.hyecheon.springadvanced.app.proxy.v1.OrderRepositoryV1
import com.hyecheon.springadvanced.trace.TraceStatus
import com.hyecheon.springadvanced.trace.logtrace.LogTrace
import org.slf4j.LoggerFactory

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/25
 */
class OrderRepositoryInterfaceProxy(
	private val target: OrderRepositoryV1,
	private val logTrace: LogTrace
) : OrderRepositoryV1 {

	override fun save(itemId: String) {
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