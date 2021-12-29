package com.hyecheon.springadvanced.config.v3_proxyfactory.advice

import com.hyecheon.springadvanced.trace.TraceStatus
import com.hyecheon.springadvanced.trace.logtrace.LogTrace
import org.aopalliance.intercept.MethodInterceptor
import org.aopalliance.intercept.MethodInvocation
import org.slf4j.LoggerFactory
import org.springframework.util.PatternMatchUtils

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/30
 */
class LogTraceAdvice(
	private val logTrace: LogTrace
) : MethodInterceptor {
	companion object {
		private val log = LoggerFactory.getLogger(this::class.java)
	}

	override fun invoke(invocation: MethodInvocation): Any? {
		val method = invocation.method

		var status: TraceStatus? = null
		try {
			status = logTrace.begin("${method.declaringClass?.simpleName}.${method.name}()")
			val result = invocation.proceed()
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