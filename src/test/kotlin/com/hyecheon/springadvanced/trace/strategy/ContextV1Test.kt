package com.hyecheon.springadvanced.trace.strategy

import com.hyecheon.springadvanced.trace.strategy.code.strategy.ContextV1
import com.hyecheon.springadvanced.trace.strategy.code.strategy.Strategy
import com.hyecheon.springadvanced.trace.strategy.code.strategy.StrategyLogic1
import com.hyecheon.springadvanced.trace.strategy.code.strategy.StrategyLogic2
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/19
 */
class ContextV1Test {
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


	@DisplayName("2. strategyV1")
	@Test
	internal fun test_2() {
		val strategyLogic1 = StrategyLogic1()
		val contextV1 = ContextV1(strategyLogic1)
		contextV1.execute()

		val strategyLogic2 = StrategyLogic2()
		val contextV2 = ContextV1(strategyLogic2)
		contextV2.execute()
	}

	@DisplayName("3. strategyV2")
	@Test
	internal fun test_3() {
		val contextV1 = ContextV1(object : Strategy {
			private val log = LoggerFactory.getLogger(this::class.java)
			override fun call() {
				log.info("비즈니스 로직1 실행")
			}
		})


		contextV1.execute()

		val contextV2 = ContextV1(object : Strategy {
			private val log = LoggerFactory.getLogger(this::class.java)
			override fun call() {
				log.info("비즈니스 로직2 실행")
			}
		})

		contextV2.execute()
	}

	@DisplayName("4. strategyV2")
	@Test
	internal fun test_4() {
		val contextV1 = ContextV1(object : Strategy {
			override fun call() {
				log.info("비즈니스 로직1 실행")
			}
		})
		contextV1.execute()

		val contextV2 = ContextV1(object : Strategy {
			override fun call() {
				log.info("비즈니스 로직2 실행")
			}
		})
		contextV2.execute()
	}
}