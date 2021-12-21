package com.hyecheon.springadvanced.app.proxy.v2

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/22
 */
class OrderRepositoryV2 {
	fun save(itemId: String) {
		if (itemId == "ex") {
			throw IllegalStateException("예외 발생!")
		}
		Thread.sleep(1000)

	}
}