package com.hyecheon.springadvanced

import com.hyecheon.springadvanced.trace.logtrace.FieldLogTrace
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/10
 */
@Configuration
class LogTraceConfig {

	@Bean
	fun logTrace() = FieldLogTrace()

}