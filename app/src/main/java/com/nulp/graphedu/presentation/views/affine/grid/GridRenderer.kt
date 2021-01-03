package com.nulp.graphedu.presentation.views.affine.grid

import android.content.Context
import com.nulp.graphedu.presentation.utils.dp
import com.nulp.graphedu.presentation.views.affine.ComplexRenderer

const val SEGMENTS_BETWEEN_ALIQUOT = 4

class GridRenderer(
    context: Context
) : ComplexRenderer() {

    companion object {
        private const val DEFAULT_SEGMENT_SIZE = 32
    }

    override val renderers: List<GridSubRenderer> = listOf(
        AffineGridLinesRenderer(context),
        AffineGridAliquotLinesRenderer(context),
        AffineGridAxesRenderer(context),
    )

    private val defaultSegmentSize = context.dp(DEFAULT_SEGMENT_SIZE)

    private var currentSegmentSize = defaultSegmentSize
    private var currentSegmentValue = 1f

    init {
        updateRenderersSegments()
    }

    override fun setScale(scale: Float) {
        super.setScale(scale)

        currentSegmentValue = 1f

        var currentScale = scale
        when {
            scale > 2f -> {
                while (currentScale > 2f) {
                    currentScale /= 2
                    currentSegmentValue /= 2
                }
            }
            scale < 0.5f -> {
                while (currentScale < 0.5f) {
                    currentScale *= 2
                    currentSegmentValue *= 2
                }
            }
        }

        currentSegmentSize = defaultSegmentSize * currentScale

        updateRenderersSegments()
    }

    private fun updateRenderersSegments() {
        renderers.forEach {
            it.setSegmentSize(currentSegmentSize)
            it.setSegmentValue(currentSegmentValue)
        }
    }
}