package com.ccooy.patterns.behavioral

/**
 * 解释器模式
 */
class Interpreter {
    companion object {
        val maleExpression = OrExpression(TerminalExpression("Robert"), TerminalExpression("John"))
        val marriedExpression = AndExpression(TerminalExpression("Julie"), TerminalExpression("Married"))
        @JvmStatic
        fun main(args: Array<String>) {
            println("John is male? ${maleExpression.interpret("John")}")
            println("Julie is a married women? ${marriedExpression.interpret("Married Julie")}")
        }
    }
}

interface Expression {
    fun interpret(context: String): Boolean
}

class TerminalExpression(var data: String) : Expression {
    override fun interpret(context: String): Boolean =
        context.contains(data)
}

class OrExpression(var exp1: Expression, var exp2: Expression) : Expression {
    override fun interpret(context: String): Boolean =
        exp1.interpret(context) || exp2.interpret(context)
}

class AndExpression(var exp1: Expression, var exp2: Expression) : Expression {
    override fun interpret(context: String): Boolean =
        exp1.interpret(context) && exp2.interpret(context)
}