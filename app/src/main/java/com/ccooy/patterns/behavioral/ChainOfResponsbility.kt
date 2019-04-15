package com.ccooy.patterns.behavioral

import java.lang.IllegalStateException

/**
 * 职责链模式
 */
interface HeadersChain {
    fun addHeader(inputHeader: String): String
}

class AuthenticationHeader(val token: String?, var next: HeadersChain? = null) : HeadersChain {
    override fun addHeader(inputHeader: String): String {
        token ?: throw IllegalStateException("Token should be not null")
        return inputHeader + "Authorization:Bearer $token\n"
            .let { next?.addHeader(it) ?: it }
    }
}

class ContentTypeHeader(val contentType: String, var next: HeadersChain? = null) : HeadersChain {
    override fun addHeader(inputHeader: String): String =
        inputHeader + "ContentType: $contentType\n"
            .let { next?.addHeader(it) ?: it }
}

class BodyPayLoad(val body: String, var next: HeadersChain? = null) : HeadersChain {
    override fun addHeader(inputHeader: String): String =
        inputHeader + "$body".let { next?.addHeader(it) ?: it }
}

class ChainOfResponsibity {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val authenticationHeader = AuthenticationHeader("123456")
            val contentTypeHeader = ContentTypeHeader("json")
            val messageBody = BodyPayLoad("Body:\n{\n\"username\"=\"dbacinski\"\n}")

            authenticationHeader.next = contentTypeHeader
            contentTypeHeader.next = messageBody

            val messageWIthAuthentication = authenticationHeader.addHeader("Headers with Authentication:\n")
            println(messageWIthAuthentication)

            val messageWithoutAuth = contentTypeHeader.addHeader("Headers:\n")
            println(messageWithoutAuth)
        }
    }
}