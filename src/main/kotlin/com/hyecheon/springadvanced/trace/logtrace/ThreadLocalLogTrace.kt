package com.hyecheon.springadvanced.trace.logtrace

import com.hyecheon.springadvanced.trace.TraceId
import com.hyecheon.springadvanced.trace.TraceStatus
import org.slf4j.LoggerFactory

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/10
 */
class ThreadLocalLogTrace : LogTrace {
	private val log = LoggerFactory.getLogger(this::class.java)

	private var traceIdHolder: ThreadLocal<TraceId?> = ThreadLocal()

	companion object {
		private const val StartPrefix = "-->"
		private const val CompletePrefix = "<--"
		private const val ExPrefix = "<x-"
	}

	override fun begin(message: String): TraceStatus {
		syncTraceId()
		val traceId = traceIdHolder.get()
		val startTimeMs = System.currentTimeMillis()
		log.info("[{}] {}{}", traceId!!.id, addSpace(StartPrefix, traceId!!.level), message)
		return TraceStatus(traceId, startTimeMs, message)
	}

	private fun syncTraceId() = run {
		val traceId = traceIdHolder.get()
		traceIdHolder.set(traceId?.createNextId() ?: TraceId())
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
		val traceId = traceIdHolder.get()
		traceId?.let {
			if (it.isFirstLevel()) traceIdHolder.remove()
			else traceIdHolder.set(it.createPreviousId())
		}
	}

	private fun addSpace(prefix: String, level: Int) = run {
		if (level >= 1) """${"|  ".repeat(level - 1)}|$prefix"""
		else ""
	}
}