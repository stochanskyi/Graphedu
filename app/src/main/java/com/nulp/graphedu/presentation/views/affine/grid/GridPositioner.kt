package com.nulp.graphedu.presentation.views.affine.grid

import com.nulp.graphedu.presentation.utils.dp
import com.nulp.graphedu.presentation.views.affine.AffineView

class GridPositioner(
    private val cx: Float,
    private val cy: Float,
    private val visibleRadius: Float,
    private val paddingDp: Float = 24f
) {

    fun position(view: AffineView) {
        val smallerSideSize = view.width.takeIf { it < view.height } ?: view.height

        val grid = view.gridRenderer

        val defaultVisibleRadius = smallerSideSize / grid.defaultSegmentSize / SEGMENTS_BETWEEN_ALIQUOT / 2f

        val paddingScale = view.context.dp(paddingDp) * 2 / smallerSideSize
        val scale = (defaultVisibleRadius / visibleRadius) * (1f - paddingScale)

        view.setLocalScale(scale)

        val translateX = -cx * grid.currentSegmentSize / grid.currentSegmentValue * SEGMENTS_BETWEEN_ALIQUOT
        val translateY = cy * grid.currentSegmentSize / grid.currentSegmentValue * SEGMENTS_BETWEEN_ALIQUOT

        view.setLocalTranslateX(translateX)
        view.setLocalTranslateY(translateY)
    }

}