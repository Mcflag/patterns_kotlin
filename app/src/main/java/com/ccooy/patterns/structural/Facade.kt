package com.ccooy.patterns.structural

class Facade {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val userRepository = UserRepository()
            val user = User("dbacinski")
            userRepository.save(user)
            val user1 = User("newuser")
            userRepository.save(user1)
            val resultUser = userRepository.findFirst()
            println("Found stored user: $resultUser")
        }
    }
}

class ComplexSystemStore(private val filePath: String) {
    private val cache: HashMap<String, String>

    init {
        println("Reading data from file: $filePath")
        cache = HashMap()
    }

    fun store(key: String, payload: String) {
        cache[key] = payload
    }

    fun read(key: String): String = cache[key] ?: ""

    fun commit() = println("Storing cached data: $cache to file: $filePath")
}

data class User(val login: String)

class UserRepository {
    private val systemPreferences = ComplexSystemStore("/data/default.prefs")

    fun save(user: User) {
        systemPreferences.store("USER_KEY", user.login)
        systemPreferences.commit()
    }

    fun findFirst(): User = User(systemPreferences.read("USER_KEY"))
}