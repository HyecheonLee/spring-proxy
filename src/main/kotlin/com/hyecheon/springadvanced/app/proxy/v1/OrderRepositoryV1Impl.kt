package com.hyecheon.springadvanced.app.proxy.v1

import java.lang.Thread.sleep

open class OrderRepositoryV1Impl : OrderRepositoryV1 {
	override fun save(itemId: String) {
		if (itemId == "ex") {
			throw IllegalStateException("예외 발생!")
		}
		sleep(1000)

	}
}