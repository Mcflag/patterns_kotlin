package com.ccooy.patterns

class Test {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = buildString {
                append("Hello, ")
                append("World!")
            }
        }
    }
}

fun buildString(
    builderAction: StringBuilder.() -> Unit
): String {
    val sb = StringBuilder()
    sb.builderAction()
    return sb.toString()
}