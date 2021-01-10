package com.nulp.graphedu.presentation.fragments.handbook.content.component

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.setPadding
import com.nulp.graphedu.presentation.utils.dpi

class HandbookLinearContainer(
    private vararg val nestedComponents: HandbookContentComponent
): HandbookContentComponent {

    companion object {
        private const val PADDING = 12
    }

    override fun createView(context: Context): View {
        return LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(context.dpi(PADDING))

            nestedComponents.forEach {
                val view = it.createView(context)
                if (view.layoutParams == null) {
                    view.layoutParams = generateLayoutParams(context, it)
                }
                addView(view)
            }
        }
    }

    private fun generateLayoutParams(
        context: Context,
        component: HandbookContentComponent
    ): LinearLayout.LayoutParams {
        return LinearLayout.LayoutParams(
            if (component.shouldWrapWidth()) LinearLayout.LayoutParams.WRAP_CONTENT
            else LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            gravity = Gravity.CENTER_HORIZONTAL
            topMargin = component.marginTop(context)
            bottomMargin = component.marginBottom(context)
        }
    }
}