package com.ccooy.patterns

import android.view.MotionEvent
import java.util.*

class Test {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

        }
    }
}

interface EventHandler {
    var next: EventHandler?
    fun handle(event: MotionEvent): Boolean
}

open class View : EventHandler {
    override var next: EventHandler? = null
    override fun handle(event: MotionEvent): Boolean {
        return onTouchEvent()
    }

    open fun onTouchEvent(): Boolean {
        return false
    }
}

open class ViewGroup : View() {
    override fun handle(event: MotionEvent): Boolean {
        if (onInterceptTouchEvent(event)) return onTouchEvent()
        else return next?.handle(event)!!
    }

    open fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return false
    }
}