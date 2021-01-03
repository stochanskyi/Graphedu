package com.nulp.graphedu.presentation.views.affine

abstract class BaseRenderer : AffineRenderer {

    protected var width: Int = 0
        private set

    protected var height: Int = 0
        private set

    protected val widthF: Float
        get() = width.toFloat()

    protected val heightF: Float
        get() = height.toFloat()

    protected var cx: Float = 0f
        private set

    protected var cy: Float = 0f
        private set

    protected var scale: Float = 1f
        private set

    protected var translateX: Float = 0f
        private set

    protected var translateY: Float = 0f
        private set

    protected var relativeLeft: Float = 0f
        private set

    protected var relativeTop: Float = 0f
        private set

    protected var relativeRight: Float = 0f
        private set

    protected var relativeBottom: Float = 0f
        private set

    override fun setSize(width: Int, height: Int) {
        this.width = width
        this.height = height
        updateAbsoluteCenter()
        updateRelativeData()
    }

    override fun setScale(scale: Float) {
        this.scale = scale
    }

    override fun setTranslateX(translateX: Float) {
        this.translateX = translateX
        updateRelativeData()
    }

    override fun setTranslateY(translateY: Float) {
        this.translateY = translateY
        updateRelativeData()
    }

    private fun updateAbsoluteCenter() {
        cx = width / 2f
        cy = height / 2f
    }

    private fun updateRelativeData() {
        relativeLeft = translateX
        relativeRight = translateX + width
        relativeTop = translateY
        relativeBottom = translateY + heightF
    }
}