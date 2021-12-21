package com.hyecheon.springadvanced.app.proxy.v3

import org.springframework.stereotype.Repository

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/22
 */
@Repository
class OrderRepositoryV3 {
	fun save(itemId: String) {
		if (itemId == "ex") {
			throw IllegalStateException("예외 발생!")
		}
		Thread.sleep(1000)

	}
}