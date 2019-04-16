package com.ccooy.patterns.behavioral

/**
 * 备忘录模式
 */
class Memento {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val originator = Originator("initial state")
            val careTaker = CareTaker()
            careTaker.saveState(originator.createMemento())

            originator.state = "State #1"
            originator.state = "State #2"
            careTaker.saveState(originator.createMemento())

            originator.state = "State #3"
            println("Current State: " + originator.state)

            originator.restore(careTaker.restore(1))
            println("Second saved state: ${originator.state}")

            originator.restore(careTaker.restore(0))
            println("First saved state: ${originator.state}")
        }
    }
}

data class MementoEntity(val state: String)

class Originator(var state: String) {
    fun createMemento(): MementoEntity {
        return MementoEntity(state)
    }

    fun restore(memento: MementoEntity) {
        state = memento.state
    }
}

class CareTaker {
    private val mementoList = ArrayList<MementoEntity>()

    fun saveState(state: MementoEntity) {
        mementoList.add(state)
    }

    fun restore(index: Int): MementoEntity {
        return mementoList[index]
    }
}