package com.ccooy.patterns.behavioral

import kotlin.properties.Delegates

interface TextChangedListener {
    fun onTextChanged(oldText: String, newText: String)
}

class PrintingTextChangedListener : TextChangedListener {
    override fun onTextChanged(oldText: String, newText: String) =
        println("Text is changed $oldText -> $newText")
}

class TextView {
    var listener: TextChangedListener? = null
    var text: String by Delegates.observable("<empty>") { _, old, new ->
        listener?.onTextChanged(old, new)
    }
}