package com.hyecheon.springadvanced.config.v2_dynamicproxy.handler

import com.hyecheon.springadvanced.trace.TraceStatus
import com.hyecheon.springadvanced.trace.logtrace.LogTrace
import org.slf4j.LoggerFactory
import org.springframework.util.PatternMatchUtils
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/28
 */
class LogTraceFilterHandler(
	private val target: Any,
	private val logTrace: LogTrace,
	private val patterns: Array<String>
) : InvocationHandler {
	private val log = LoggerFactory.getLogger(this::class.java)
	override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {

		val methodName = method?.name ?: ""
		if (!PatternMatchUtils.simpleMatch(patterns, methodName)) {
			return method?.invoke(target, *(args ?: arrayOfNulls<Any>(0)))
		}

		var status: TraceStatus? = null
		try {
			status = logTrace.begin("${method?.declaringClass?.simpleName}.${method?.name}()")
			val result = method?.invoke(target, *(args ?: arrayOfNulls<Any>(0)))
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