package com.hyecheon.springadvanced.trace.hellotrace

import com.hyecheon.springadvanced.trace.TraceId
import com.hyecheon.springadvanced.trace.TraceStatus
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/05
 */
@Component
class HelloTraceV1 {
	private val log = LoggerFactory.getLogger(this::class.java)

	companion object {
		private const val StartPrefix = "-->"
		private const val CompletePrefix = "<--"
		private const val ExPrefix = "<x-"
	}

	fun begin(message: String) = run {
		val traceId = TraceId()
		val startTimeMs = System.currentTimeMillis()
		log.info("[{}] {}{}", traceId.id, addSpace(StartPrefix, traceId.level), message)
		TraceStatus(traceId, startTimeMs, message)
	}

	fun end(status: TraceStatus) = run {
		complete(status, null)
	}

	fun exception(status: TraceStatus, e: Exception) = run {
		complete(status, e)
	}

	private fun complete(status: TraceStatus, e: Exception?) = run {
		val stopTimeMs = System.currentTimeMillis()
		val resultTimeMs = stopTimeMs - status.startTimeMs
		val traceId = status.traceId
		if (e == null) {
			log.info(
				"[{}] {}{} time={}ms",
				traceId.id,
				addSpace(CompletePrefix, traceId.level),
				status.message,
				resultTimeMs
			)
		} else {
			log.info(
				"[{}] {}{} time={}ms ex={}",
				traceId.id,
				addSpace(ExPrefix, traceId.level),
				status.message,
				resultTimeMs,
				e.toString()
			)
		}
	}

	fun addSpace(prefix: String, level: Int) = run {
		if (level >= 1) """${"|  ".repeat(level - 1)}|$prefix"""
		else ""
	}
}