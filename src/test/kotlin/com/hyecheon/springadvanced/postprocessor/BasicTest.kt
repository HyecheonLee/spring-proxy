package com.hyecheon.springadvanced.postprocessor

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/31
 */
class BasicTest {
	private val log = LoggerFactory.getLogger(this::class.java)

	@DisplayName("1. basicConfig")
	@Test
	internal fun test_1() {
		val applicationContext = AnnotationConfigApplicationContext(BasicConfig::class.java)

		val a = applicationContext.getBean("beanA", A::class.java)
		a.helloA()

		Assertions.assertThatThrownBy {
			applicationContext.getBean("beanB", B::class.java)
		}.isInstanceOf(NoSuchBeanDefinitionException::class.java)
	}



	@Configuration
	class BasicConfig {
		private val log = LoggerFactory.getLogger(this::class.java)

		@Bean(name = ["beanA"])
		fun a() = run {
			A()
		}

	}

	class A {
		private val log = LoggerFactory.getLogger(this::class.java)
		fun helloA() = run {
			log.info("hello A")
		}
	}

	class B {
		private val log = LoggerFactory.getLogger(this::class.java)
		fun helloB() = run {
			log.info("hello B")
		}
	}
}