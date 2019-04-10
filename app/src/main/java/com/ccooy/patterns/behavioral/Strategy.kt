package com.ccooy.patterns.behavioral

/**
 * 策略模式的意图是封装算法，让算法独立，并且之间可以相互，让行为的变化独立于拥有行为的客户。
 */
class Strategy {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val inputString = "LORem IpsUm DOLOR sit aMeT"
            val lowerCasePrinter = Printer(lowerCaseFormatter)
            lowerCasePrinter.printString(inputString)

            val upperCasePrinter = Printer(upperCaseFormatter)
            upperCasePrinter.printString(inputString)

            val prefixPrinter = Printer { "Prefix: $it" }
            prefixPrinter.printString(inputString)

            val suffixPrinter = Printer(suffixFormatter)
            suffixPrinter.printString(inputString)
        }
    }
}

class Printer(private val stringFormatStrategy: (String) -> String) {
    fun printString(string: String) {
        println(stringFormatStrategy(string))
    }
}

val lowerCaseFormatter: (String) -> String = { it.toLowerCase() }
val upperCaseFormatter = { it: String -> it.toUpperCase() }
val suffixFormatter = { it: String -> "$it -> Suffix" }