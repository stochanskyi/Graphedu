package com.nulp.graphedu.presentation.views.affine.grid

import com.nulp.graphedu.presentation.views.affine.AffineRenderer

interface GridRendererComponent : AffineRenderer {

    fun initDefaultSegmentSize(size: Float)

    fun setSegmentSize(size: Float)
    fun setSegmentValue(value: Float)

}