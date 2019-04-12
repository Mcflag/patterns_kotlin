package com.ccooy.patterns.creational

/**
 * 单例模式，kotlin中常用的两种方法
 */
class Singleton {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Start")
            PrinterDriver.print()
            PrinterDriver.print()

            Printer.instance.print()
            Printer.instance.print()
        }
    }
}

/**
 * 最简单的使用方法，对象声明
 */
object PrinterDriver {
    init {
        println("Initializing with object: $this")
    }

    fun print(): PrinterDriver =
        apply { println("Printing with object: $this") }
}

/**
 * 另一种写法，懒加载
 */
class Printer private constructor() {
    init {
        println("Initializing with object: $this")
    }

    private object Holder {
        val INSTANCE = Printer()
    }

    companion object {
        val instance: Printer by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            Holder.INSTANCE
        }
    }

    fun print() {
        println("Printing with object: $this")
    }
}