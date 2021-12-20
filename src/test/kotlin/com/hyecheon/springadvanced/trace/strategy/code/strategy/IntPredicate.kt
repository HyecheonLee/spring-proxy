package com.hyecheon.springadvanced.trace.strategy.code.strategy

@FunctionalInterface
fun interface IntPredicate {
	fun accept(value: Int): Boolean
}