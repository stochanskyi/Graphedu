package com.nulp.graphedu.presentation.fragments.handbook.content.component

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.core.view.updatePadding
import com.agog.mathdisplay.MTMathView
import com.nulp.graphedu.R
import com.nulp.graphedu.presentation.utils.dp
import com.nulp.graphedu.presentation.utils.dpi

class HandbookContentFormula(
    private val formula: String
): HandbookContentComponent {

    companion object {
        private const val TEXT_SIZE = 14f
    }

    override fun createView(context: Context) = MTMathView(context).apply {
        updatePadding(
            top = context.dpi(8),
            bottom = context.dpi(8),
        )
        fontSize = context.dp(TEXT_SIZE)
        textColor = ContextCompat.getColor(context, R.color.text_color)
        textAlignment = MTMathView.MTTextAlignment.KMTTextAlignmentCenter
        latex = formula
    }
}