package com.nulp.graphedu.presentation.views.affine.grid.coordinate

import android.graphics.Paint
import android.graphics.RectF
import android.text.TextPaint
import com.nulp.graphedu.presentation.utils.baselineToAscent
import com.nulp.graphedu.presentation.utils.baselineToDescent

object TextRightAlignBackgroundResolver: TextBackgroundResolver {

    override val align: Paint.Align = Paint.Align.RIGHT

    override fun resolve(x: Float, y: Float, paint: TextPaint, text: String): RectF {
        return RectF(
            x - paint.measureText(text),
            y - paint.baselineToAscent,
            x,
            y + paint.baselineToDescent
        )
    }
}