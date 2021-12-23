package com.hyecheon.springadvanced.proxy.code

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/23
 */
class ProxyPatternClient(
	private val subject: Subject
) {
	fun execute() = run {
		subject.operation()
	}
}