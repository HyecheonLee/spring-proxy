package com.hyecheon.springadvanced.trace.template

import com.hyecheon.springadvanced.trace.template.code.AbstractTemplate
import com.hyecheon.springadvanced.trace.template.code.SubClassLogic1
import com.hyecheon.springadvanced.trace.template.code.SubClassLogic2
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/15
 */
class TemplateMethodTest {
	private val log = LoggerFactory.getLogger(this::class.java)


	@DisplayName("1. templateMethodV0")
	@Test
	internal fun test_1() {
		logic1()
		logic2()
	}

	fun logic1() = run {
		val startTime = System.currentTimeMillis()
		//비즈니스 로직 실행
		log.info("비즈니스 로직1 실행")
		//비즈니스 로직 종료
		val endTime = System.currentTimeMillis()
		val resultTime = endTime - startTime
		log.info("resultTime={}", resultTime)
	}

	fun logic2() = run {
		val startTime = System.currentTimeMillis()
		//비즈니스 로직 실행
		log.info("비즈니스 로직2 실행")
		//비즈니스 로직 종료
		val endTime = System.currentTimeMillis()
		val resultTime = endTime - startTime
		log.info("resultTime={}", resultTime)
	}


	@DisplayName("2. templateMethodV1")
	@Test
	internal fun test_2() {
		val template1 = SubClassLogic1()
		template1.execute()

		val template2 = SubClassLogic2()
		template2.execute()
	}

	@DisplayName("3. templateMethodV3")
	@Test
	internal fun test_3() {
		val template1 = object : AbstractTemplate() {
			override fun call() {
				log.info("비즈니스 로직1 실행")
			}
		}
		log.info("클래스 이름1={}", template1.javaClass)
		template1.execute()

		val template2 = object : AbstractTemplate() {
			override fun call() {
				log.info("비즈니스 로직2 실행")
			}
		}
		log.info("클래스 이름2={}", template2.javaClass)
		template2.execute()
	}
}