package com.ccooy.patterns.structural

import java.util.*

class Flyweight {
    companion object {
        private val colors = arrayOf("Red", "Green", "Blue", "White", "Black")

        @JvmStatic
        fun main(args: Array<String>) {
            for (i in 0 until 20) {
                val circle = ShapeFactory.getCircle(getRandomColor()) as CircleF
                circle.x = getRandomX()
                circle.y = getRandomY()
                circle.radius = 100
                circle.draw()
            }
        }

        private fun getRandomColor() =
            colors[Random().nextInt(colors.size)]

        private fun getRandomX() = (Math.random() * 100).toInt()

        private fun getRandomY() = (Math.random() * 100).toInt()
    }
}

interface ShapeF {
    fun draw()
}

class CircleF(private var color: String) : ShapeF {
    var x: Int = 0
    var y: Int = 0
    var radius: Int = 0

    override fun draw() {
        println("Circle: Draw() [Color: $color, x: $x, y: $y, radius: $radius]")
    }
}

class ShapeFactory {
    companion object {
        private val circleMap = HashMap<String, ShapeF>()
        fun getCircle(color: String): ShapeF {
            var circle = circleMap[color]
            if (circle == null) {
                circle = CircleF(color)
                circleMap[color] = circle
                println("Creating circle of color : $color")
            }
            return circle
        }
    }
}