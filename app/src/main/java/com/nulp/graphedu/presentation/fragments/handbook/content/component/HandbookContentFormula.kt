package com.nulp.graphedu.presentation.fragments.handbook.content.component

import android.content.Context
import androidx.core.content.ContextCompat
import com.agog.mathdisplay.MTMathView
import com.nulp.graphedu.R
import com.nulp.graphedu.presentation.utils.dp
import com.nulp.graphedu.presentation.utils.dpi

class HandbookContentFormula(
    private val formula: String
): HandbookContentComponent {

    companion object {
        private const val TEXT_SIZE = 18f
    }

    override fun createView(context: Context) = MTMathView(context).apply {
        fontSize = context.dp(TEXT_SIZE)
        textColor = ContextCompat.getColor(context, R.color.text_color)
        textAlignment = MTMathView.MTTextAlignment.KMTTextAlignmentCenter
        latex = formula
    }

    override fun marginTop(context: Context) = context.dpi(8)
    override fun marginBottom(context: Context) = context.dpi(8)
}