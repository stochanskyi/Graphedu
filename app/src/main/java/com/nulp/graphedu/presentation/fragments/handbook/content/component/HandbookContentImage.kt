package com.nulp.graphedu.presentation.fragments.handbook.content.component

import android.content.Context
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.nulp.graphedu.presentation.utils.dpi

class HandbookContentImage(
    @DrawableRes
    private val imageRes: Int,
    private val wrapWidth: Boolean = false
) : HandbookContentComponent {

    override fun createView(context: Context) = ImageView(context).apply {
        scaleType = ImageView.ScaleType.FIT_CENTER
        adjustViewBounds = true
        setImageResource(imageRes)
    }

    override fun shouldWrapWidth(): Boolean {
        return wrapWidth
    }

    override fun marginTop(context: Context) = context.dpi(8)
    override fun marginBottom(context: Context) = context.dpi(8)
}