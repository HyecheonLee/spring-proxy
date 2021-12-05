package com.hyecheon.springadvanced.trace

import java.util.*

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/05
 */
data class TraceId(
	val id: String = createId(),
	val level: Int = 0
) {
	companion object {
		private fun createId(): String = UUID.randomUUID().toString().substring(0, 8)
	}

	fun createNextId() = copy(level = level + 1)

	fun createPreviousId() = copy(level = level - 1)

	fun isFirstLevel() = level == 0


}