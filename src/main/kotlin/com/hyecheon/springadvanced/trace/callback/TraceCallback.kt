package com.hyecheon.springadvanced.trace.callback

import org.slf4j.LoggerFactory

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/12/20
 */
fun interface TraceCallback<T> {
	fun call(): T
}