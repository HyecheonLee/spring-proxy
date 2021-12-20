package com.hyecheon.springadvanced.trace.strategy

import com.hyecheon.springadvanced.trace.strategy.code.template.Callback
import com.hyecheon.springadvanced.trace.strategy.code.template.TimeLogTemplate
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/20
 */
class TemplateCallbackTest {
	private val log = LoggerFactory.getLogger(this::class.java)

	@DisplayName("1. 템플릿 콜백 패턴 -익명 내부 클래스")
	@Test
	internal fun test_1() {
		val template = TimeLogTemplate()
		template.execute(object : Callback {
			override fun call() {
				log.info("비즈니스 로직1 실행")
			}
		})
		template.execute(object : Callback {
			override fun call() {
				log.info("비즈니스 로직2 실행")
			}
		})
	}


	@DisplayName("2. 템플릿 콜백 패턴 - 람다")
	@Test
	internal fun test_2() {
		val template = TimeLogTemplate()
		template.execute { log.info("비즈니스 로직1 실행") }
		template.execute { log.info("비즈니스 로직2 실행") }
	}
}