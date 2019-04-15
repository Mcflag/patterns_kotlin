package com.ccooy.patterns.structural

/**
 * 代理模式
 */
class ProtectionProxy {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val securedFile = SecuredFile(NormalFile())
            with(securedFile) {
                read("readme.md")
                password = "secret"
                read("readme.md")
            }
        }
    }
}

interface File {
    fun read(name: String)
}

class NormalFile : File {
    override fun read(name: String) = println("Reading file: $name")
}

class SecuredFile(private val normalFile: File) : File {
    var password: String = ""

    override fun read(name: String) {
        if (password == "secret") {
            println("Password is correct: $password")
            normalFile.read(name)
        } else {
            println("Incorrect password. Access denied!")
        }
    }
}