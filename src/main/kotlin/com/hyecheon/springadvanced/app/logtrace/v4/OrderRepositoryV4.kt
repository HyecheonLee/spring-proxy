package com.hyecheon.springadvanced.app.logtrace.v4

import com.hyecheon.springadvanced.trace.logtrace.LogTrace
import com.hyecheon.springadvanced.trace.template.AbstractTemplate
import org.springframework.stereotype.Repository
import java.lang.Thread.sleep

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/04
 */
@Repository
class OrderRepositoryV4(
	private val trace: LogTrace
) {
	fun save(itemId: String) = run {
		val template = object : AbstractTemplate<Unit>(trace) {
			override fun call() {
				if (itemId == "ex") {
					throw IllegalStateException("예외 발생")
				}
				sleep(1000)
			}
		}
		template.execute("OrderRepository.save()")
	}
}