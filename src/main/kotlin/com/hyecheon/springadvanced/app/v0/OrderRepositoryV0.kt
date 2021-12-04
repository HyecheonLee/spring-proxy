package com.hyecheon.springadvanced.app.v0

import org.springframework.stereotype.Repository
import java.lang.Thread.sleep

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/04
 */
@Repository
class OrderRepositoryV0(

) {
	fun save(itemId: String) = run {
		if (itemId == "ex") {
			throw IllegalStateException("예외 발생")
		}
		sleep(1000)
	}
}