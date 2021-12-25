package com.hyecheon.springadvanced.decorator

import com.hyecheon.springadvanced.decorator.code.DecoratorPatternClient
import com.hyecheon.springadvanced.decorator.code.MessageDecorator
import com.hyecheon.springadvanced.decorator.code.RealComponent
import com.hyecheon.springadvanced.decorator.code.TimeDecorator
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/25
 */
class DecoratorPatternTest {

	@DisplayName("1. noDecorator")
	@Test
	internal fun test_1() {
		val realComponent = RealComponent()
		val client = DecoratorPatternClient(realComponent)
		client.execute()
	}

	@DisplayName("2. decorator-1")
	@Test
	internal fun test_2() {
		val realComponent = RealComponent()
		val messageDecorator = MessageDecorator(realComponent)
		val client = DecoratorPatternClient(messageDecorator)
		client.execute()
	}

	@DisplayName("3. decorator-2")
	@Test
	internal fun test_3() {
		val realComponent = RealComponent()
		val messageDecorator = MessageDecorator(realComponent)
		val timeDecorator = TimeDecorator(messageDecorator)
		val client = DecoratorPatternClient(timeDecorator)
		client.execute()
	}
}