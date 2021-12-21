package com.hyecheon.springadvanced.app.logtrace.v3

import com.hyecheon.springadvanced.trace.logtrace.LogTrace
import org.springframework.beans.factory.ObjectFactory
import org.springframework.stereotype.Service

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/04
 */
@Service
class OrderServiceV3(
	private val orderRepositoryV3: OrderRepositoryV3,
	private val traceObject: ObjectFactory<LogTrace>

) {
	fun orderItem(itemId: String) = run {
		val trace = traceObject.`object`
		val status = trace.begin("OrderService.orderItem()")
		try {
			orderRepositoryV3.save(itemId = itemId)
			trace.end(status)
			"ok"
		} catch (e: Exception) {
			trace.exception(status, e)
			throw e
		}
	}
}