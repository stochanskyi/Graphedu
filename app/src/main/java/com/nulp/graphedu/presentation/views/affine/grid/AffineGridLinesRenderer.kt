package com.nulp.graphedu.presentation.views.affine.grid

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.nulp.graphedu.presentation.utils.dp
import com.nulp.graphedu.presentation.views.affine.BaseRenderer
import com.nulp.graphedu.presentation.views.affine.DRAW_OUT_OF_BOUNDS

class AffineGridLinesRenderer(
    context: Context
): BaseRenderer(), GridSubRenderer {

    companion object {
        // Grid line
        private const val GRID_LINE_COLOR = Color.LTGRAY
        private const val GRID_LINE_WIDTH = 0.5f
    }

    private val gridLinePaint = Paint().apply {
        color = GRID_LINE_COLOR
        strokeWidth = context.dp(GRID_LINE_WIDTH)
    }

    private var segmentSize: Float = 0f

    override fun setSegmentSize(size: Float) {
        this.segmentSize = size
    }

    override fun render(canvas: Canvas) {
        drawXLines(canvas)
        drawYLines(canvas)
    }

    private fun drawXLines(canvas: Canvas) {
        var currentY = translateY % segmentSize
        var i = 1
        while (currentY < heightF + DRAW_OUT_OF_BOUNDS) {
            if (i % 5 != 0) {
                drawXLine(canvas, currentY)
            }
            currentY += segmentSize
            i++
        }
    }

    private fun drawXLine(canvas: Canvas, y: Float) {
        canvas.drawLine(
            0f, y,
            widthF, y,
            gridLinePaint
        )
    }

    private fun drawYLines(canvas: Canvas) {
        var currentX = translateX % segmentSize
        var i = 1
        while (currentX < widthF + DRAW_OUT_OF_BOUNDS) {
            if (i % 5 != 0) {
                drawYLine(canvas, currentX)
            }
            currentX += segmentSize
            i++
        }
    }

    private fun drawYLine(canvas: Canvas, x: Float) {
        canvas.drawLine(
            x, 0f,
            x, heightF,
            gridLinePaint
        )
    }
}