package com.ccooy.patterns.javaee

/**
 * 前端控制器模式
 */
class FrontControllerTest {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val frontController = FrontController()
            frontController.dispatchRequest("HOME")
            frontController.dispatchRequest("STUDENT")
        }
    }
}

class HomeViewA {
    fun show() {
        println("Displaying Home Page")
    }
}

class StudentViewA {
    fun show() {
        println("Displaying Student Page")
    }
}

class Dispatcher {
    private var studentView: StudentViewA = StudentViewA()
    private var homeView: HomeViewA = HomeViewA()

    fun dispatch(request: String) {
        if (request.equals("STUDENT", true)) {
            studentView.show()
        } else {
            homeView.show()
        }
    }
}

class FrontController {
    private var dispatcher: Dispatcher = Dispatcher()

    private fun isAuthenticUser(): Boolean {
        println("User is authenticated successfully.")
        return true
    }

    private fun trackRequest(request: String) {
        println("Page requested: $request")
    }

    fun dispatchRequest(request: String) {
        trackRequest(request)
        if (isAuthenticUser()) {
            dispatcher.dispatch(request)
        }
    }
}

