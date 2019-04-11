package com.ccooy.patterns.javaee

class ServiceLocatorTest {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var service = ServiceLocator.getService("Service1")
            service.execute()
            service = ServiceLocator.getService("Service2")
            service.execute()
            service = ServiceLocator.getService("Service1")
            service.execute()
            service = ServiceLocator.getService("Service2")
            service.execute()
        }
    }
}

interface Service {
    fun getName(): String
    fun execute()
}

class Service1 : Service {
    override fun execute() {
        println("Executing Service1")
    }

    override fun getName(): String = "Service1"
}

class Service2 : Service {
    override fun execute() {
        println("Executing Service2")
    }

    override fun getName(): String = "Service2"
}

class InitialContext {
    fun lookup(jndiName: String): Service? {
        if (jndiName.equals("SERVICE1", true)) {
            println("Looking up and creating a new Service1 object")
            return Service1()
        } else if (jndiName.equals("SERVICE2", true)) {
            println("Looking up and creating a new Service2 object")
            return Service2()
        }
        return null
    }
}

class Cache {
    private var services = ArrayList<Service>()

    fun getService(serviceName: String): Service? {
        for (service in services) {
            if (service.getName().equals(serviceName, true)) {
                println("Returning cached $serviceName object")
                return service
            }
        }
        return null
    }

    fun addService(newService: Service) {
        var exists = false
        for (service in services) {
            if (service.getName().equals(newService.getName(), true)) {
                exists = true
            }
        }
        if (!exists) {
            services.add(newService)
        }
    }
}

class ServiceLocator {
    companion object {
        private var cache = Cache()

        fun getService(jndiName: String): Service {
            val service = cache.getService(jndiName)
            if (service != null) {
                return service
            }
            val context = InitialContext()
            val service1 = context.lookup(jndiName) as Service
            cache.addService(service1)
            return service1
        }
    }
}
