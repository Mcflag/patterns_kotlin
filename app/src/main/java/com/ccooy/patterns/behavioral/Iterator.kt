package com.ccooy.patterns.behavioral

class IteratorPattern {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val nameRepository = NameRepository()
            val iter = nameRepository.getIterator()
            while (iter.hasNext()) {
                println("Name: ${iter.next()}")
            }
        }
    }
}

interface Iterator {
    fun hasNext(): Boolean
    fun next(): String?
}

interface Container {
    fun getIterator(): Iterator
}

class NameRepository : Container {
    companion object {
        val names: Array<String> = arrayOf("Robert", "John", "Julie", "Lora")
    }

    override fun getIterator(): Iterator {
        return NameIterator()
    }

    class NameIterator : Iterator {
        var index: Int = 0

        override fun hasNext(): Boolean {
            return index < names.size
        }

        override fun next(): String? {
            if (this.hasNext()) {
                return names[index++]
            }
            return null
        }
    }
}
