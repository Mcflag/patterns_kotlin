package com.ccooy.patterns.structural

import java.math.BigDecimal

/**
 * 适配器模式
 */
class Adapter {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val celsiusTemperature = CelsiusTemperature(0.0)
            val fahrenheitTemperature = FahrenheitTemperature(celsiusTemperature)

            celsiusTemperature.temperature = 36.6
            println("${celsiusTemperature.temperature} C -> ${fahrenheitTemperature.temperature} F")

            fahrenheitTemperature.temperature = 100.0
            println("${fahrenheitTemperature.temperature} F -> ${celsiusTemperature.temperature} C")
        }
    }
}

interface Temperature {
    var temperature: Double
}

class CelsiusTemperature(override var temperature: Double) : Temperature

class FahrenheitTemperature(private var celsiusTemperature: CelsiusTemperature) : Temperature {
    override var temperature: Double
        get() = convertCelsiusToFahrenheit(celsiusTemperature.temperature)
        set(tempreatureInF) {
            celsiusTemperature.temperature = convertFahrenheitToCelsius(tempreatureInF)
        }

    private fun convertFahrenheitToCelsius(f: Double): Double =
        ((BigDecimal.valueOf(f).setScale(2) - BigDecimal(32)) * BigDecimal(5) / BigDecimal(9)).toDouble()

    private fun convertCelsiusToFahrenheit(c: Double): Double =
        ((BigDecimal.valueOf(c).setScale(2) * BigDecimal(9) / BigDecimal(5)) + BigDecimal(32)).toDouble()
}