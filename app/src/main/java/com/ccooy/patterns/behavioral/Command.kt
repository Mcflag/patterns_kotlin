package com.ccooy.patterns.behavioral

/**
 * 命令模式：意图是对动作的解耦，把一个动作的执行分为执行对象和执行行为，使两者互相独立。
 */
class Command {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            CommandProcessor()
                .addToQueue(OrderAddCommand(1L))
                .addToQueue(OrderAddCommand(2L))
                .addToQueue(OrderPayCommand(2L))
                .addToQueue(OrderPayCommand(1L))
                .processCommands()
        }
    }
}

interface OrderCommand {
    fun execute()
}

class OrderAddCommand(val id: Long) : OrderCommand {
    override fun execute() = println("Adding  order with id: $id")
}

class OrderPayCommand(val id: Long) : OrderCommand {
    override fun execute() = println("Paying for order with id: $id")
}

class CommandProcessor {
    private val queue = ArrayList<OrderCommand>()

    fun addToQueue(orderCommand: OrderCommand): CommandProcessor =
        apply {
            queue.add(orderCommand)
        }

    fun processCommands(): CommandProcessor =
        apply {
            queue.forEach { it.execute() }
            queue.clear()
        }
}