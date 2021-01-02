package com.nulp.graphedu.presentation.views.affine.grid

import android.content.Context
import com.nulp.graphedu.presentation.utils.dp
import com.nulp.graphedu.presentation.views.affine.ComplexRenderer

class GridRenderer(
    context: Context
) : ComplexRenderer() {

    companion object {
        private const val DEFAULT_SEGMENT_SIZE = 50f
    }

    override val renderers: List<GridSubRenderer> = listOf(
        AffineGridLinesRenderer(context),
        AffineGridAxesRenderer(context),
    )

    private var currentSegmentSize = context.dp(DEFAULT_SEGMENT_SIZE)

    init {
        renderers.forEach {
            it.setSegmentSize(currentSegmentSize)
        }
    }

    override fun setScale(scale: Float) {
        super.setScale(scale)
        renderers.forEach {
            it.setSegmentSize(DEFAULT_SEGMENT_SIZE * scale)
        }
    }

}