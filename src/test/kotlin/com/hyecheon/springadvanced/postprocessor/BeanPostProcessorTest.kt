package com.hyecheon.springadvanced.postprocessor

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/31
 */
class BeanPostProcessorTest {
	private val log = LoggerFactory.getLogger(this::class.java)

	@DisplayName("1. beanPostProcessorConfig")
	@Test
	internal fun test_1() {
		val applicationContext = AnnotationConfigApplicationContext(BeanPostProcessorConfig::class.java)

		val a = applicationContext.getBean("beanA", B::class.java)
		a.helloB()

		Assertions.assertThatThrownBy {
			applicationContext.getBean("beanB", A::class.java)
		}.isInstanceOf(NoSuchBeanDefinitionException::class.java)
	}


	@Configuration
	class BeanPostProcessorConfig {
		private val log = LoggerFactory.getLogger(this::class.java)

		@Bean(name = ["beanA"])
		fun a() = run {
			A()
		}

		@Bean
		fun helloPostProcessor() = run {
			AToBPostProcessor()
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

	class AToBPostProcessor : BeanPostProcessor {
		private val log = LoggerFactory.getLogger(this::class.java)
		override fun postProcessAfterInitialization(bean: Any, beanName: String): Any? {
			log.info("beanName={} bean={}", beanName, bean)
			return if (bean is A) {
				B()
			} else {
				bean
			}
		}
	}
}