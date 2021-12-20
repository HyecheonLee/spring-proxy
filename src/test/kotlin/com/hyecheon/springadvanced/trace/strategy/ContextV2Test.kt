package com.hyecheon.springadvanced.trace.strategy

import com.hyecheon.springadvanced.trace.strategy.code.strategy.ContextV2
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
class ContextV2Test {

	private val log = LoggerFactory.getLogger(this::class.java)


	@DisplayName("1. strategyV1")
	@Test
	internal fun test_1() {
		val contextV2 = ContextV2()
		contextV2.execute(StrategyLogic1())
		contextV2.execute(StrategyLogic2())
	}

	@DisplayName("2. strategyV2")
	@Test
	internal fun test_2() {
		val contextV2 = ContextV2()
		contextV2.execute(object : Strategy {
			override fun call() {
				log.info("비지니스 로직 1 실행")
			}
		})
		contextV2.execute(object : Strategy {
			override fun call() {
				log.info("비지니스 로직 2 실행")
			}
		})
	}
}