package com.nulp.graphedu.presentation.fragments.handbook.content.component

import android.content.Context
import android.graphics.Typeface
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.view.updatePadding
import com.nulp.graphedu.R
import com.nulp.graphedu.presentation.utils.dpi

class HandbookContentImage(
    @DrawableRes
    private val imageRes: Int,
    private val wrapWidth: Boolean = false
) : HandbookContentComponent {

    override fun createView(context: Context) = ImageView(context).apply {
        updatePadding(
            top = context.dpi(8),
            bottom = context.dpi(8),
        )
        scaleType = ImageView.ScaleType.FIT_CENTER
        adjustViewBounds = true
        setImageResource(imageRes)
    }

    override fun shouldWrapWidth(): Boolean {
        return wrapWidth
    }
}