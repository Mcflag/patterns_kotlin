package com.ccooy.patterns.behavioral

class State {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val authorizationPresenter = AuthorizationPresenter()

            authorizationPresenter.loginUser("admin")
            println(authorizationPresenter)

            authorizationPresenter.logoutUser()
            println(authorizationPresenter)
        }
    }
}

sealed class AuthorizationState

object Unauthorized : AuthorizationState()

class Authorized(val userName: String) : AuthorizationState()

class AuthorizationPresenter {
    private var state: AuthorizationState = Unauthorized

    fun loginUser(userLogin: String) {
        state = Authorized(userLogin)
    }

    fun logoutUser() {
        state = Unauthorized
    }

    val isAuthorized: Boolean
        get() = when (state) {
            is Authorized -> true
            is Unauthorized -> false
        }

    val userLogin: String
        get() {
            val state = this.state
            return when (state) {
                is Authorized -> state.userName
                is Unauthorized -> "Unknown"
            }
        }

    override fun toString(): String = "User '$userLogin' is logged in: $isAuthorized"
}
