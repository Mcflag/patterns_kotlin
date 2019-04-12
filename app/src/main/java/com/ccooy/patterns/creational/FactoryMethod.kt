package com.ccooy.patterns.creational

/**
 * 工厂方法模式
 */
class FactoryMethod {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val greeceCurrency = CurrencyFactory().currencyForCountry(Greece("")).code
            println("Greece currency: $greeceCurrency")
            val usaCurrency = CurrencyFactory().currencyForCountry(Country.USA).code
            println("USA currency: $usaCurrency")
        }
    }
}

sealed class Country {
    object USA : Country()
    object CHINA : Country()
}

object Spain : Country()
class Greece(val someProperty: String) : Country()
data class Canada(val someProperty: String) : Country()

class Currency(
    val code: String
)

class CurrencyFactory {
    fun currencyForCountry(country: Country): Currency =
        when (country) {
            is Greece -> Currency("EUR")
            is Spain -> Currency("EUR")
            is Country.USA -> Currency("USD")
            is Canada -> Currency("CAD")
            is Country.CHINA -> Currency("RMB")
        }
}