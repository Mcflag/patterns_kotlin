package com.ccooy.patterns.behavioral

class Mediator {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val mediator = ChatMediator()
            val john = ChatUser(mediator, "John")

            mediator.addUser(ChatUser(mediator, "Alice"))
                .addUser(ChatUser(mediator, "Bob"))
                .addUser(john)

            john.send("Hi everyone!")
        }
    }
}

class ChatUser(private val mediator: ChatMediator, val name: String) {
    fun send(msg: String) {
        println("$name: Sending Message=$msg")
        mediator.sendMessage(msg, this)
    }

    fun receive(msg: String) {
        println("$name: Message received:$msg")
    }
}

class ChatMediator {
    private val users: MutableList<ChatUser> = ArrayList()

    fun sendMessage(msg: String, user: ChatUser) {
        users.filter { it != user }.forEach {
            it.receive(msg)
        }
    }

    fun addUser(user: ChatUser): ChatMediator = apply { users.add(user) }
}