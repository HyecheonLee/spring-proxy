package com.hyecheon.springadvanced.config.v4_postprocessor.postprocessor

import org.slf4j.LoggerFactory
import org.springframework.aop.Advisor
import org.springframework.aop.framework.ProxyFactory
import org.springframework.beans.factory.config.BeanPostProcessor

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2022/01/01
 */
class PackageLogTraceProxyPostProcessor(
	private val basePackage: String,
	private val advisor: Advisor
) : BeanPostProcessor {
	companion object {
		private val log = LoggerFactory.getLogger(this::class.java)
	}

	override fun postProcessAfterInitialization(bean: Any, beanName: String): Any? {
		log.info("param beanName={} bean={}", beanName, bean.javaClass)

		val packageName = bean.javaClass.packageName
		if (!packageName.startsWith(basePackage)) {
			return bean
		}
		val proxyFactory = ProxyFactory(bean)
		proxyFactory.addAdvisor(advisor)

		val proxy = proxyFactory.proxy

		log.info("create proxy: target={} proxy={}", bean.javaClass, proxy.javaClass)

		return proxy
	}
}