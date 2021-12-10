package com.hyecheon.springadvanced.trace.logtrace

import com.hyecheon.springadvanced.trace.TraceStatus

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/10
 */
interface LogTrace {

	fun begin(message: String): TraceStatus

	fun end(status: TraceStatus)

	fun exception(status: TraceStatus, e: Exception)

}