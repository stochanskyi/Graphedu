package com.nulp.graphedu.presentation.fragments.handbook.content.component

import android.content.Context
import android.graphics.Typeface
import android.view.Gravity
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.updatePadding
import com.nulp.graphedu.R
import com.nulp.graphedu.presentation.utils.dpi

class HandbookContentTitle(
    private val title: CharSequence
) : HandbookContentComponent {

    companion object {
        private const val TEXT_SIZE = 16f
    }

    override fun createView(context: Context) = TextView(context).apply {
        updatePadding(
            top = context.dpi(8),
            bottom = context.dpi(4)
        )
        gravity = Gravity.CENTER_HORIZONTAL
        typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD)
        textSize = TEXT_SIZE
        setTextColor(ContextCompat.getColor(context, R.color.text_color))
        text = title
    }
}