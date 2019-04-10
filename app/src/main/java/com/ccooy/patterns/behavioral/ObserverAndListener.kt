package com.ccooy.patterns.behavioral

import kotlin.properties.Delegates

class ObserverAndListener {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val textView = TextView().apply {
                listener = PrintingTextChangedListener()
            }
            with(textView) {
                text = "asdfasdfasdfasdf"
                text = "893834849832492742379487"
            }
        }
    }
}

interface TextChangedListener {
    fun onTextChanged(oldText: String, newText: String)
}

class PrintingTextChangedListener : TextChangedListener {
    override fun onTextChanged(oldText: String, newText: String) =
        println("Text is changed $oldText -> $newText")
}

class TextView {
    var listener: TextChangedListener? = null
    var text: String by Delegates.observable("<EMPTY>") { _, old, new ->
        listener?.onTextChanged(old, new)
    }
}