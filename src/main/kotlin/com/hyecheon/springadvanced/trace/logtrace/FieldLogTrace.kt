package com.hyecheon.springadvanced.trace.logtrace

import com.hyecheon.springadvanced.trace.TraceId
import com.hyecheon.springadvanced.trace.TraceStatus
import org.slf4j.LoggerFactory

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/10
 */
class FieldLogTrace : LogTrace {
	private val log = LoggerFactory.getLogger(this::class.java)

	private var traceIdHolder: TraceId? = null

	companion object {
		private const val StartPrefix = "-->"
		private const val CompletePrefix = "<--"
		private const val ExPrefix = "<x-"
	}

	override fun begin(message: String): TraceStatus {
		syncTraceId()
		val traceId = traceIdHolder!!
		val startTimeMs = System.currentTimeMillis()
		log.info("[{}] {}{}", traceId.id, addSpace(StartPrefix, traceId.level), message)
		return TraceStatus(traceId, startTimeMs, message)
	}

	private fun syncTraceId() = run {
		traceIdHolder = traceIdHolder?.createNextId() ?: TraceId()
	}

	override fun end(status: TraceStatus) {
		complete(status, null)
	}

	override fun exception(status: TraceStatus, e: Exception) {
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
		releaseTraceId()
	}

	private fun releaseTraceId() = run {
		traceIdHolder = traceIdHolder?.let {
			if (it.isFirstLevel()) null
			else it.createPreviousId()
		}
	}

	private fun addSpace(prefix: String, level: Int) = run {
		if (level >= 1) """${"|  ".repeat(level - 1)}|$prefix"""
		else ""
	}
}