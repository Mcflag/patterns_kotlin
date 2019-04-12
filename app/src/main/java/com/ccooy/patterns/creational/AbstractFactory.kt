package com.ccooy.patterns.creational

import java.lang.IllegalArgumentException

/**
 * 抽象工厂模式
 */
class AbstractFactory {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val plantFactory = PlantFactory.createFactory<OrangePlant>()
            val plant = plantFactory.makePlant()
            println("Created plant: $plant")
            println("Created plant: ${PlantFactory.createFactory<ApplePlant>().makePlant()}")
        }
    }
}

interface Plant

class OrangePlant : Plant

class ApplePlant : Plant

abstract class PlantFactory {
    abstract fun makePlant(): Plant

    companion object {
        inline fun <reified T : Plant> createFactory(): PlantFactory =
            when (T::class) {
                OrangePlant::class -> OrangeFactory()
                ApplePlant::class -> AppleFactory()
                else -> throw IllegalArgumentException()
            }
    }
}

class AppleFactory : PlantFactory() {
    override fun makePlant(): Plant = ApplePlant()
}

class OrangeFactory : PlantFactory() {
    override fun makePlant(): Plant = OrangePlant()
}