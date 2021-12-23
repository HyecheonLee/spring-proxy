package com.hyecheon.springadvanced.proxy

import com.hyecheon.springadvanced.proxy.code.CacheProxy
import com.hyecheon.springadvanced.proxy.code.ProxyPatternClient
import com.hyecheon.springadvanced.proxy.code.RealSubject
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/23
 */
class ProxyPatternTest {

	@DisplayName("1. noProxy")
	@Test
	internal fun test_1() {
		val subject = RealSubject()
		val proxyPatternClient = ProxyPatternClient(subject)
		proxyPatternClient.execute()
		proxyPatternClient.execute()
		proxyPatternClient.execute()
	}

	@DisplayName("2. cacheProxy")
	@Test
	internal fun test_2() {
		val client = ProxyPatternClient(CacheProxy(RealSubject()))
		client.execute()
		client.execute()
		client.execute()
	}
}