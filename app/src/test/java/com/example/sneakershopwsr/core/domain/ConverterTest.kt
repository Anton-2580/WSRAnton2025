package com.example.sneakershopwsr.core.domain


/**
 * Test on [Converter]
 */
abstract class ConverterTest<F, T> {
    abstract val converter: Converter<F, T>

    abstract var fromData: F
    abstract var toData: T

    open fun testConvertFromTo() {
        val actual: T = converter.convertFromTo(fromData)
        val expected: T = toData

        assert(actual == expected)
    }

    open fun testConvertToFrom() {
        val actual: F = converter.convertToFrom(toData)
        val expected: F = fromData

        assert(actual == expected)
    }
}