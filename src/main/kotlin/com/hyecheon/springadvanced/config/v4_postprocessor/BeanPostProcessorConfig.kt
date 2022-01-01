package com.hyecheon.springadvanced.config.v4_postprocessor

import com.hyecheon.springadvanced.config.ProxyAppV1Config
import com.hyecheon.springadvanced.config.ProxyAppV2Config
import com.hyecheon.springadvanced.config.v3_proxyfactory.advice.LogTraceAdvice
import com.hyecheon.springadvanced.config.v4_postprocessor.postprocessor.PackageLogTraceProxyPostProcessor
import com.hyecheon.springadvanced.trace.logtrace.LogTrace
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.aop.support.NameMatchMethodPointcut
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2022/01/01
 */
@Configuration
@Import(value = [ProxyAppV1Config::class, ProxyAppV2Config::class])
class BeanPostProcessorConfig {

	@Bean
	fun logTraceProxyPostProcessor(logTrace: LogTrace) = run {
		PackageLogTraceProxyPostProcessor("com.hyecheon.springadvanced.app", getAdvisor(logTrace))
	}

	fun getAdvisor(logTrace: LogTrace) = run {
		val pointcut = NameMatchMethodPointcut()
		pointcut.setMappedNames("request*", "order*", "save*")

		val advice = LogTraceAdvice(logTrace)
		DefaultPointcutAdvisor(pointcut, advice)
	}
}