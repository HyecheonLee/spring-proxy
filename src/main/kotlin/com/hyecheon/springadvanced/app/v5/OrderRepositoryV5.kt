package com.hyecheon.springadvanced.app.v5

import com.hyecheon.springadvanced.trace.callback.TraceTemplate
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
class OrderRepositoryV5(
	private val trace: TraceTemplate
) {
	fun save(itemId: String) = run {
		trace.execute("OrderRepository.save()") {
			if (itemId == "ex") {
				throw IllegalStateException("예외 발생")
			}
			sleep(1000)
		}
	}
}