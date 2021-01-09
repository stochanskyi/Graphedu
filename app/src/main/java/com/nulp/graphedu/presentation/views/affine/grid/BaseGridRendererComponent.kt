package com.nulp.graphedu.presentation.views.affine.grid

import com.nulp.graphedu.presentation.views.affine.BaseRenderer

abstract class BaseGridRendererComponent : BaseRenderer(), GridRendererComponent {

    protected var defaultSegmentSize: Float = 1f
        private set

    protected var segmentSize: Float = 0f
        private set

    protected var segmentValue: Float = 0f
        private set

    override fun initDefaultSegmentSize(size: Float) {
        this.defaultSegmentSize = size
    }

    override fun setSegmentSize(size: Float) {
        this.segmentSize = size
    }

    override fun setSegmentValue(value: Float) {
        this.segmentValue = value
    }

}