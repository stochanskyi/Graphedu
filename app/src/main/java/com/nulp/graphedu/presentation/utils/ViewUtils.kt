package com.nulp.graphedu.presentation.utils

import android.view.View
import androidx.annotation.ColorInt
import androidx.fragment.app.Fragment
import java.lang.IllegalStateException

fun View.setTint(@ColorInt tintColor: Int) {
    background.setTint(tintColor)
}

@Suppress("UNCHECKED_CAST")
fun <T> Fragment.parentAsListener(): T {
    return parentFragment as? T
        ?: activity as? T
        ?: throw IllegalStateException("${this::class.simpleName} does not have parent, who implements T")
}