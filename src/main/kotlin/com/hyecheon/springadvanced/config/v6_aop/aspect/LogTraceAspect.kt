package com.hyecheon.springadvanced.config.v6_aop.aspect

import com.hyecheon.springadvanced.trace.TraceStatus
import com.hyecheon.springadvanced.trace.logtrace.LogTrace
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2022/01/03
 */
@Aspect
class LogTraceAspect(
	private val logTrace: LogTrace
) {
	companion object {
		private val log = LoggerFactory.getLogger(this::class.java)
	}

	@Around("execution(* com.hyecheon.springadvanced.app..*(..))")
	fun execute(joinPoint: ProceedingJoinPoint): Any? = run {
		var status: TraceStatus? = null
		try {
			val message = joinPoint.signature.toShortString()
			status = logTrace.begin(message)
			val result = joinPoint.proceed()
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