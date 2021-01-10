package com.nulp.graphedu.presentation.fragments.handbook.content.component

import android.content.Context
import android.graphics.Typeface
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.nulp.graphedu.R

open class HandbookContentText(
    private val text: CharSequence
) : HandbookContentComponent {

    companion object {
        private const val TEXT_SIZE = 14f
    }

    override fun createView(context: Context) = TextView(context).apply {
        typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL)
        textSize = TEXT_SIZE
        setTextColor(ContextCompat.getColor(context, R.color.text_color))
        text = this@HandbookContentText.text
    }
}