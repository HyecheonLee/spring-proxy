package com.hyecheon.springadvanced.trace.callback

import com.hyecheon.springadvanced.trace.TraceStatus
import com.hyecheon.springadvanced.trace.logtrace.LogTrace
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/20
 */
@Component
class TraceTemplate(
	private val trace: LogTrace
) {
	private val log = LoggerFactory.getLogger(this::class.java)

	fun <T> execute(message: String, callback: TraceCallback<T>) = run {
		val status: TraceStatus = trace.begin(message)
		try {
			val result = callback.call()
			trace.end(status)
			result
		} catch (e: Exception) {
			trace.exception(status, e)
			throw e
		}
	}
}