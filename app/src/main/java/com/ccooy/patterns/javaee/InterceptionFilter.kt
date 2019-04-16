package com.ccooy.patterns.javaee

/**
 * 拦截过滤器模式
 */
class InterceptionFilter {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val filterManager = FilterManager(Target())
            filterManager.setFilter(AuthenticationFilter())
            filterManager.setFilter(DebugFilter())
            val client = ClientB()
            client.filterManager = filterManager
            client.sendRequest("HOME")
        }
    }
}

interface Filter {
    fun execute(request: String)
}

class AuthenticationFilter : Filter {
    override fun execute(request: String) {
        println("Authentication request: $request")
    }
}

class DebugFilter : Filter {
    override fun execute(request: String) {
        println("request log: $request")
    }
}

class Target {
    fun execute(request: String) {
        println("Executing request: $request")
    }
}

class FilterChain {
    private var filters = ArrayList<Filter>()
    lateinit var target: Target

    fun addFilter(filter: Filter) {
        filters.add(filter)
    }

    fun execute(request: String) {
        for (filter in filters) {
            filter.execute(request)
        }
        target.execute(request)
    }
}

class FilterManager(target: Target) {
    private var filterChain = FilterChain()

    init {
        filterChain.target = target
    }

    fun setFilter(filter: Filter) {
        filterChain.addFilter(filter)
    }

    fun filterRequest(request: String) {
        filterChain.execute(request)
    }
}

class ClientB {
    lateinit var filterManager: FilterManager

    fun sendRequest(request: String) {
        filterManager.filterRequest(request)
    }
}