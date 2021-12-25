package com.hyecheon.springadvanced.concreateproxy

import com.hyecheon.springadvanced.concreateproxy.code.ConcreteClient
import com.hyecheon.springadvanced.concreateproxy.code.ConcreteLogic
import com.hyecheon.springadvanced.concreateproxy.code.TimeProxy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/25
 */
class ConcreteProxyTest {

	@DisplayName("1. noProxy")
	@Test
	internal fun test_1() {
		val concreteLogic = ConcreteLogic()
		val client = ConcreteClient(concreteLogic)
		client.execute()
	}


	@DisplayName("2. addProxy")
	@Test
	internal fun test_2() {
		val concreteLogic = ConcreteLogic()
		val client = ConcreteClient(TimeProxy(concreteLogic))
		client.execute()
	}

}