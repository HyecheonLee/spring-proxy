package com.hyecheon.springadvanced.trace

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/05
 */
data class TraceStatus(val traceId: TraceId, val startTimeMs: Long, val message: String)