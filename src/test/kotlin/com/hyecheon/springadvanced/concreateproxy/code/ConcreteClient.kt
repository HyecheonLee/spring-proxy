package com.hyecheon.springadvanced.concreateproxy.code

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/25
 */
class ConcreteClient(
	private val concreteLogic: ConcreteLogic
) {
	fun execute() = run {
		concreteLogic.operation()
	}
}