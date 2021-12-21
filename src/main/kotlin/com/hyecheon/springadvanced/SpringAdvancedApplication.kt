package com.hyecheon.springadvanced

import com.hyecheon.springadvanced.config.ProxyAppV1Config
import com.hyecheon.springadvanced.config.ProxyAppV2Config
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

//@Import(ProxyAppV1Config::class)
@Import(value = [ProxyAppV1Config::class, ProxyAppV2Config::class])
@SpringBootApplication(scanBasePackages = ["com.hyecheon.springadvanced.app.proxy"])
class SpringAdvancedApplication

fun main(args: Array<String>) {
	runApplication<SpringAdvancedApplication>(*args)
}
