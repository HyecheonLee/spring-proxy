package com.hyecheon.springadvanced.config.v2_dynamicproxy.handler

import com.hyecheon.springadvanced.trace.TraceStatus
import com.hyecheon.springadvanced.trace.logtrace.LogTrace
import org.slf4j.LoggerFactory
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/28
 */
class LogTraceBasicHandler(
	private val target: Any,
	private val logTrace: LogTrace
) : InvocationHandler {
	private val log = LoggerFactory.getLogger(this::class.java)
	override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {
		var status: TraceStatus? = null
		try {
			status = logTrace.begin("${method?.declaringClass?.simpleName}.${method?.name}()")

			val result = if (args == null) method?.invoke(target) else method?.invoke(target, *args)
			logTrace.end(status)
			return result
		} catch (e: Exception) {
			status?.let {
				logTrace.exception(status, e)
			}
			throw e
		}
	}
}