package com.itechart.stocker

import androidx.databinding.ObservableInt

interface ErrorCleanerTextWatcher {
    fun cleanErrorIfTextChanged(before: Int, count: Int, fieldError: ObservableInt)
}

val errorCleanerTextWatcher = object : ErrorCleanerTextWatcher {
    override fun cleanErrorIfTextChanged(before: Int, count: Int, fieldError: ObservableInt) {
        if (before != count) {
            fieldError.set(0)
        }
    }
}
