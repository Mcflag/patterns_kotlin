package com.ccooy.patterns.creational

class Singleton {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Start")
            val printerFirst = PrinterDriver.print()
            val printerSecond = PrinterDriver.print()
        }
    }
}

object PrinterDriver {
    init {
        println("Initializing with object: $this")
    }

    fun print(): PrinterDriver =
        apply { println("Printing with object: $this") }
}