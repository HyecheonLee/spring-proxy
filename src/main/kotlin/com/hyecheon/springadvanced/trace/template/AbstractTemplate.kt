package com.hyecheon.springadvanced.trace.template

import com.hyecheon.springadvanced.trace.TraceStatus
import com.hyecheon.springadvanced.trace.logtrace.LogTrace

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/18
 */
abstract class AbstractTemplate<T>(
	val trace: LogTrace
) {
	fun execute(message: String) = run {
		val status: TraceStatus = trace.begin(message)
		try {
			val result = call()
			trace.end(status)
			result
		} catch (e: Exception) {
			trace.exception(status, e)
			throw e
		}
	}

	protected abstract fun call(): T
}