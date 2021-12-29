package com.hyecheon.springadvanced

import com.hyecheon.springadvanced.config.ProxyAppV1Config
import com.hyecheon.springadvanced.config.ProxyAppV2Config
import com.hyecheon.springadvanced.config.v1_proxy.ConcreteProxyConfig
import com.hyecheon.springadvanced.config.v1_proxy.InterfaceProxyConfig
import com.hyecheon.springadvanced.config.v2_dynamicproxy.DynamicProxyBasicConfig
import com.hyecheon.springadvanced.config.v2_dynamicproxy.DynamicProxyFilterConfig
import com.hyecheon.springadvanced.config.v3_proxyfactory.ProxyFactoryConfigV1
import com.hyecheon.springadvanced.config.v3_proxyfactory.ProxyFactoryConfigV2
import com.hyecheon.springadvanced.trace.logtrace.ThreadLocalLogTrace
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import

//@Import(ProxyAppV1Config::class)
//@Import(value = [ProxyAppV1Config::class, ProxyAppV2Config::class])
//@Import(value = [InterfaceProxyConfig::class])
//@Import(value = [ConcreteProxyConfig::class])
//@Import(value = [DynamicProxyBasicConfig::class])
//@Import(value = [DynamicProxyFilterConfig::class])
//@Import(value = [ProxyFactoryConfigV1::class])
@Import(value = [ProxyFactoryConfigV2::class])
@SpringBootApplication(scanBasePackages = ["com.hyecheon.springadvanced.app.proxy"])
class SpringAdvancedApplication {
	@Bean
	fun logTrace() = run {
		ThreadLocalLogTrace()
	}
}

fun main(args: Array<String>) {
	runApplication<SpringAdvancedApplication>(*args)
}
