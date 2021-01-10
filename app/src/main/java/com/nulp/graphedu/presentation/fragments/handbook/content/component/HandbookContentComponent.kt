package com.nulp.graphedu.presentation.fragments.handbook.content.component

import android.content.Context
import android.view.View

interface HandbookContentComponent {
    fun createView(context: Context): View

    fun shouldWrapWidth(): Boolean = false

    fun marginTop(context: Context): Int = 0
    fun marginBottom(context: Context): Int = 0
}