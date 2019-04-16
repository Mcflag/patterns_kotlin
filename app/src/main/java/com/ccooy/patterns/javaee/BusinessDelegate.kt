package com.ccooy.patterns.javaee

/**
 * 业务代表模式
 */
class BusinessDelegateTest {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val businessDelegate = BusinessDelegate()
            businessDelegate.serviceType = "EJB"

            val client = Client(businessDelegate)
            client.doTask()

            businessDelegate.serviceType = "JMS"
            client.doTask()
        }
    }
}

interface BusinessService {
    fun doProcessing()
}

class EJBService : BusinessService {
    override fun doProcessing() {
        println("Processing task by invoking EJB Service")
    }
}

class JMSService : BusinessService {
    override fun doProcessing() {
        println("Processing task by invoking JMS Service")
    }
}

class BusinessLookUp {
    fun getBusinessService(serviceType: String): BusinessService {
        return when (serviceType.equals("EJB", true)) {
            true -> EJBService()
            false -> JMSService()
        }
    }
}

class BusinessDelegate {
    private var lookupService = BusinessLookUp()
    private lateinit var businessService: BusinessService
    lateinit var serviceType: String

    fun doTask() {
        businessService = lookupService.getBusinessService(serviceType)
        businessService.doProcessing()
    }
}

class Client(private var businessService: BusinessDelegate) {
    fun doTask() {
        businessService.doTask()
    }
}

