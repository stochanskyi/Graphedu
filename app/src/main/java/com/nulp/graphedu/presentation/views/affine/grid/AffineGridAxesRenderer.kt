package com.nulp.graphedu.presentation.views.affine.grid

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.text.TextPaint
import com.nulp.graphedu.presentation.utils.baselineToTop
import com.nulp.graphedu.presentation.utils.dp
import com.nulp.graphedu.presentation.views.affine.BaseRenderer
import com.nulp.graphedu.presentation.views.affine.DRAW_OUT_OF_BOUNDS

class AffineGridAxesRenderer(
    context: Context
): BaseRenderer(), GridSubRenderer {

    companion object {
        // Axis line
        private const val AXIS_LINE_COLOR = Color.BLACK
        private const val AXIS_LINE_WIDTH = 2f

        // Axis text
        private const val AXIS_LABEL_COLOR = Color.BLACK
        private const val AXIS_LABEL_SIZE = 18
        private const val AXIS_LABEL_MARGIN = 8
        private const val AXIS_LABEL_AXIS_MARGIN = 4
    }

    private val axisLinePaint: Paint = Paint().apply {
        color = AXIS_LINE_COLOR
        strokeWidth = context.dp(AXIS_LINE_WIDTH)
    }

    private val axisLabelPaint = TextPaint().apply {
        color = AXIS_LABEL_COLOR
        textSize = context.dp(AXIS_LABEL_SIZE)
        typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD)
    }

    private val axisLabelMargin = context.dp(AXIS_LABEL_MARGIN)
    private val axisLabelAxisMargin = context.dp(AXIS_LABEL_AXIS_MARGIN)

    override fun setSegmentSize(size: Float) {
    }

    override fun render(canvas: Canvas) {
        drawXAxis(canvas)
        drawYAxis(canvas)
        drawXAxisLabel(canvas)
        drawYAxisLabel(canvas)
    }

    private fun drawXAxis(canvas: Canvas) {
        if (translateY > cy + DRAW_OUT_OF_BOUNDS) return
        if (translateY < -cy - DRAW_OUT_OF_BOUNDS) return

        canvas.drawLine(
            0f, translatedCy,
            widthF, translatedCy,
            axisLinePaint
        )
    }

    private fun drawYAxis(canvas: Canvas) {
        if (translateX > cx + DRAW_OUT_OF_BOUNDS) return
        if (translateX < -cx - DRAW_OUT_OF_BOUNDS) return

        canvas.drawLine(
            translatedCx, 0f,
            translatedCx, heightF,
            axisLinePaint
        )
    }

    private fun drawXAxisLabel(canvas: Canvas) {
        val textWidth = axisLabelPaint.measureText("X")
        canvas.drawText(
            "X",
            width - textWidth - axisLabelMargin,
            translatedCy - axisLabelAxisMargin,
            axisLabelPaint
        )
    }

    private fun drawYAxisLabel(canvas: Canvas) {
        canvas.drawText(
            "Y",
            translatedCx + axisLabelAxisMargin,
            axisLabelPaint.baselineToTop + axisLabelAxisMargin,
            axisLabelPaint
        )
    }
}