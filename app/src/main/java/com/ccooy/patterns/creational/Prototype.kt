package com.ccooy.patterns.creational

/**
 * 原型模式，用Java的思路写kotlin的实现
 * 实际上没有必要，直接使用data class即可实现原型模式。
 */
class Prototype {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            ShapeCache.loadCache()
            val clonedShape: Shape = ShapeCache.getShape("1")
            println("Shape: ${clonedShape.type}")
            val clonedShape2 = ShapeCache.getShape("2")
            println("Shape: ${clonedShape2.type}")
            val clonedShape3 = ShapeCache.getShape("3")
            println("Shape: ${clonedShape3.type}")
        }
    }
}

abstract class Shape(var id: String, var type: String) : Cloneable {
    abstract fun draw()

    public override fun clone(): Shape {
        return super.clone() as Shape
    }
}

class Rectangle(id: String, type: String = "Rectangle") : Shape(id, type) {
    override fun draw() {
        println("Inside Rectangle::draw() method.")
    }
}

class Square(id: String, type: String = "Square") : Shape(id, type) {
    override fun draw() {
        println("Inside Square::draw() method.")
    }
}

class Circle(id: String, type: String = "Circle") : Shape(id, type) {
    override fun draw() {
        println("Inside Circle::draw() method.")
    }
}

class ShapeCache {
    companion object {
        var shapeMap: MutableMap<String, Shape> = mutableMapOf()
        fun getShape(shapeId: String): Shape {
            return (shapeMap[shapeId] as Shape).clone()
        }

        fun loadCache() {
            val circle = Circle("1")
            shapeMap[circle.id] = circle
            val square = Square("2")
            shapeMap[square.id] = square
            val rectangle = Rectangle("3")
            shapeMap[rectangle.id] = rectangle
        }
    }
}
