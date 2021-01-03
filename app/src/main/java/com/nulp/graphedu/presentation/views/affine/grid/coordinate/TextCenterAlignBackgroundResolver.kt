package com.nulp.graphedu.presentation.views.affine.grid.coordinate

import android.graphics.Paint
import android.graphics.RectF
import android.text.TextPaint
import com.nulp.graphedu.presentation.utils.baselineToAscent
import com.nulp.graphedu.presentation.utils.baselineToDescent

object TextCenterAlignBackgroundResolver: TextBackgroundResolver {

    override val align: Paint.Align = Paint.Align.CENTER

    override fun resolve(x: Float, y: Float, paint: TextPaint, text: String): RectF {
        val halfWidth = paint.measureText(text) / 2f
        return RectF(
            x - halfWidth,
            y - paint.baselineToAscent,
            x + halfWidth,
            y + paint.baselineToDescent
        )
    }
}