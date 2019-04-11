package com.ccooy.patterns.javaee

class CompositeEntityTest {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val client = ClientF()
            client.setData("Test", "Data")
            client.printData()
            client.setData("Second Test", "Data1")
            client.printData()
        }
    }
}

data class DependentObject1(var data: String)

data class DependentObject2(var data: String)

class CoarseGrainedObject {
    private lateinit var do1: DependentObject1
    private lateinit var do2: DependentObject2

    fun setData(data1: String, data2: String) {
        do1 = DependentObject1(data1)
        do2 = DependentObject2(data2)
    }

    fun getData(): Array<String> {
        return arrayOf(do1.data, do2.data)
    }
}

class CompositeEntity {
    private var cgo = CoarseGrainedObject()

    fun setData(data1: String, data2: String) {
        cgo.setData(data1, data2)
    }

    fun getData(): Array<String> {
        return cgo.getData()
    }
}

class ClientF {
    private var compositeEntity = CompositeEntity()

    fun setData(data1: String, data2: String) {
        compositeEntity.setData(data1, data2)
    }

    fun printData() {
        for (i in 0 until compositeEntity.getData().size) {
            println("Data: ${compositeEntity.getData()[i]}")
        }
    }
}