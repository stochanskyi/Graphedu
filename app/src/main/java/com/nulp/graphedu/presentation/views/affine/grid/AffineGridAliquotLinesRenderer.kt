package com.nulp.graphedu.presentation.views.affine.grid

import android.content.Context
import android.graphics.*
import android.text.TextPaint
import com.nulp.graphedu.presentation.utils.*
import com.nulp.graphedu.presentation.views.affine.DRAW_OUT_OF_BOUNDS
import com.nulp.graphedu.presentation.views.affine.grid.coordinate.*
import kotlin.math.max
import kotlin.math.min

// TODO do not draw below axes
class AffineGridAliquotLinesRenderer(
    context: Context
) : BaseGridRendererComponent() {

    companion object {
        // Grid aliquot line
        private const val ALIQUOT_LINE_COLOR = Color.DKGRAY
        private const val ALIQUOT_LINE_WIDTH = 1.5f

        // Grid coordinate text
        private const val COORDINATE_TEXT_COLOR = Color.DKGRAY
        private const val COORDINATE_TEXT_SIZE = 16
        private const val COORDINATE_TEXT_STROKE_COLOR = Color.WHITE
        private const val COORDINATE_TEXT_STROKE_WIDTH = 4

        private const val COORDINATE_TEXT_MARGIN = 4
    }

    private val coordinateTextMargin = context.dp(COORDINATE_TEXT_MARGIN)

    private val aliquotLinePaint = Paint().apply {
        color = ALIQUOT_LINE_COLOR
        strokeWidth = context.dp(ALIQUOT_LINE_WIDTH)
    }

    private val coordinateTextPaint = TextPaint().apply {
        style = Paint.Style.FILL
        color = COORDINATE_TEXT_COLOR
        textSize = context.sp(COORDINATE_TEXT_SIZE)
        typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL)
    }
    private val coordinateStrokeTextPaint = TextPaint(coordinateTextPaint).apply {
        style = Paint.Style.STROKE
        color = COORDINATE_TEXT_STROKE_COLOR
        strokeWidth = context.sp(COORDINATE_TEXT_STROKE_WIDTH)
    }

    private val textBackgroundPaint = Paint().apply {
        style = Paint.Style.FILL
        color = Color.WHITE
        alpha = 64
    }

    private val coordinateTextHalfHeight = coordinateTextPaint.measuredHeight("0") / 2f
    private val bottomTextYOffset = coordinateTextMargin
    private val bottomTextStickyY = coordinateTextPaint.height + coordinateTextMargin

    private var quintSize: Float = 0f

    override fun setSegmentSize(size: Float) {
        super.setSegmentSize(size)
        quintSize = segmentSize * SEGMENTS_BETWEEN_ALIQUOT
    }

    override fun render(canvas: Canvas) {
        drawHorizontalLines(canvas)
        drawVerticalLines(canvas)
    }

    private fun drawHorizontalLines(canvas: Canvas) {
        var currentY = translatedCy % quintSize
        var value = -resolveIndexRelativeToAxis(translatedCy, quintSize) * segmentValue
        while (currentY < heightF + DRAW_OUT_OF_BOUNDS) {
            drawHorizontalLine(canvas, currentY)
            drawYCoordinate(canvas, currentY, value.toString())
            currentY += quintSize
            value -= segmentValue
        }
    }

    private fun drawHorizontalLine(canvas: Canvas, y: Float) {
        canvas.drawLine(
            0f, y,
            widthF, y,
            aliquotLinePaint
        )
    }

    private fun drawYCoordinate(canvas: Canvas, y: Float, text: String) {
        if (y.isNotInBounds(0f, heightF)) return
        var textY = y + coordinateTextHalfHeight
        textY = textY.inBounds(
            coordinateTextPaint.baselineToAscent + coordinateTextMargin,
            heightF - coordinateTextMargin
        )
        val textWidth = coordinateTextPaint.measureText(text)
        when {
            translatedCx < textWidth + coordinateTextMargin * 2 -> {
                drawYCoordinateLeft(canvas, textY, text)
            }
            translatedCx > widthF -> {
                drawYCoordinateRight(canvas, textY, text)
            }
            else -> drawYCoordinateNearAxis(canvas, translatedCx, textY, text)
        }
    }

    private fun drawYCoordinateNearAxis(canvas: Canvas, axisX: Float, y: Float, text: String) {
        canvas.drawTextWithBackground(
            axisX - coordinateTextMargin,
            y,
            text,
            TextRightAlignBackgroundResolver
        )
    }

    private fun drawYCoordinateLeft(canvas: Canvas, y: Float, text: String) {
        canvas.drawTextWithBackground(
            coordinateTextMargin,
            y,
            text,
            TextLeftAlignBackgroundResolver
        )
    }

    private fun drawYCoordinateRight(canvas: Canvas, y: Float, text: String) {
        canvas.drawTextWithBackground(
            widthF - coordinateTextMargin,
            y,
            text,
            TextRightAlignBackgroundResolver
        )
    }

    private fun drawVerticalLines(canvas: Canvas) {
        var currentX = translatedCx % quintSize
        var value = resolveIndexRelativeToAxis(translatedCx, quintSize) * segmentValue
        while (currentX < widthF + DRAW_OUT_OF_BOUNDS) {
            drawVerticalLine(canvas, currentX)
            drawXCoordinate(canvas, currentX, value.toString())
            currentX += quintSize
            value += segmentValue
        }
    }

    private fun drawVerticalLine(canvas: Canvas, x: Float) {
        canvas.drawLine(
            x, 0f,
            x, heightF,
            aliquotLinePaint
        )
    }

    private fun drawXCoordinate(canvas: Canvas, x: Float, text: String) {
        if (x.isNotInBounds(0f, widthF)) return
        val textXMargin = coordinateTextPaint.measureText(text) / 2f + coordinateTextMargin
        val textX = x.inBounds(textXMargin, widthF - textXMargin)
        when {
            translatedCy < 0 -> {
                drawXCoordinateTop(canvas, textX, text)
            }
            translatedCy > heightF - bottomTextStickyY -> {
                drawXCoordinateBottom(canvas, textX, text)
            }
            else -> drawXCoordinateNearAxis(canvas, textX, translatedCy, text)
        }
    }

    private fun drawXCoordinateNearAxis(canvas: Canvas, x: Float, axisY: Float, text: String) {
        canvas.drawTextWithBackground(
            x,
            axisY + coordinateTextPaint.baselineToTop + coordinateTextMargin,
            text,
            TextCenterAlignBackgroundResolver
        )
    }

    private fun drawXCoordinateTop(canvas: Canvas, x: Float, text: String) {
        canvas.drawTextWithBackground(
            x,
            coordinateTextPaint.baselineToTop + coordinateTextMargin,
            text,
            TextCenterAlignBackgroundResolver
        )
    }

    private fun drawXCoordinateBottom(canvas: Canvas, x: Float, text: String) {
        canvas.drawTextWithBackground(
            x,
            heightF - bottomTextYOffset,
            text,
            TextCenterAlignBackgroundResolver
        )
    }

    private fun Canvas.drawTextWithBackground(
        x: Float, y: Float,
        text: String,
        resolver: TextBackgroundResolver
    ) {
        coordinateTextPaint.textAlign = resolver.align
        coordinateStrokeTextPaint.textAlign = resolver.align
        drawRect(
            resolver.resolve(x, y, coordinateTextPaint, text),
            textBackgroundPaint
        )
        drawText(text, x, y, coordinateStrokeTextPaint)
        drawText(text, x, y, coordinateTextPaint)
    }

    private fun Float.inBounds(from: Float, to: Float): Float {
        return max(from, min(this, to))
    }

    private fun Float.isNotInBounds(from: Float, to: Float): Boolean {
        return this > to || this < from
    }
}