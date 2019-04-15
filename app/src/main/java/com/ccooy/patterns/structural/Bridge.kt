package com.ccooy.patterns.structural

/**
 * 桥接模式
 */
class Bridge {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val redCircle = Circle(100, 100, 10, RedCircle())
            val greenCircle = Circle(100, 100, 10, GreenCircle())
            redCircle.draw()
            greenCircle.draw()
        }
    }
}

interface DrawAPI {
    fun drawCircle(radius: Int, x: Int, y: Int)
}

class RedCircle : DrawAPI {
    override fun drawCircle(radius: Int, x: Int, y: Int) =
        println("Drawing Circle[ color: red, radius: $radius, x: $x, y: $y]")
}

class GreenCircle : DrawAPI {
    override fun drawCircle(radius: Int, x: Int, y: Int) =
        println("Drawing Circle[ color: green, radius: $radius, x: $x, y: $y]")
}

abstract class Shape(drawAPI: DrawAPI) {
    abstract fun draw()
}

class Circle(var x: Int, var y: Int, var radius: Int, var drawAPI: DrawAPI) : Shape(drawAPI) {
    override fun draw() =
        drawAPI.drawCircle(radius, x, y)
}