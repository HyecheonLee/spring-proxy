package com.hyecheon.springadvanced.config.v6_aop

import com.hyecheon.springadvanced.config.ProxyAppV1Config
import com.hyecheon.springadvanced.config.ProxyAppV2Config
import com.hyecheon.springadvanced.config.v6_aop.aspect.LogTraceAspect
import com.hyecheon.springadvanced.trace.logtrace.LogTrace
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2022/01/03
 */
@Configuration
@Import(value = arrayOf(ProxyAppV1Config::class, ProxyAppV2Config::class))
class AopConfig {

	@Bean
	fun logTraceAspect(logTrace: LogTrace) = run {
		LogTraceAspect(logTrace)
	}
}