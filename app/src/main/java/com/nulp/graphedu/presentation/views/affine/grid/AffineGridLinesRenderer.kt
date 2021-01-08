package com.nulp.graphedu.presentation.views.affine.grid

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.nulp.graphedu.presentation.utils.dp
import com.nulp.graphedu.presentation.views.affine.DRAW_OUT_OF_BOUNDS

class AffineGridLinesRenderer(
    context: Context
): BaseGridRendererComponent() {

    companion object {
        // Grid line
        private const val GRID_LINE_COLOR = Color.LTGRAY
        private const val GRID_LINE_WIDTH = 1f
    }

    private val gridLinePaint = Paint().apply {
        color = GRID_LINE_COLOR
        strokeWidth = context.dp(GRID_LINE_WIDTH)
    }

    override fun render(canvas: Canvas) {
        drawHorizontalLines(canvas)
        drawVerticalLines(canvas)
    }

    private fun drawHorizontalLines(canvas: Canvas) {
        var currentY = translatedCy % segmentSize
        var i = resolveIndexRelativeToAxis(translatedCy, segmentSize)

        while (currentY < heightF + DRAW_OUT_OF_BOUNDS) {
            if (i !=0 && i % SEGMENTS_BETWEEN_ALIQUOT != 0) {
                drawHorizontalLine(canvas, currentY)
            }
            currentY += segmentSize
            i++
        }
    }

    private fun drawHorizontalLine(canvas: Canvas, y: Float) {
        canvas.drawLine(
            0f, y,
            widthF, y,
            gridLinePaint
        )
    }

    private fun drawVerticalLines(canvas: Canvas) {
        var currentX = translatedCx % segmentSize
        var i = resolveIndexRelativeToAxis(translatedCx, segmentSize)

        while (currentX < widthF + DRAW_OUT_OF_BOUNDS) {
            if (i !=0 && i % SEGMENTS_BETWEEN_ALIQUOT != 0) {
                drawVerticalLine(canvas, currentX)
            }
            currentX += segmentSize
            i++
        }
    }

    private fun drawVerticalLine(canvas: Canvas, x: Float) {
        canvas.drawLine(
            x, 0f,
            x, heightF,
            gridLinePaint
        )
    }
}