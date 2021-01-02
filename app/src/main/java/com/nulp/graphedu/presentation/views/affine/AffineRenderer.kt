package com.nulp.graphedu.presentation.views.affine

import android.graphics.Canvas

interface AffineRenderer {

    fun setSize(width: Int, height: Int)

    fun setScale(scale: Float)

    fun setTranslateX(translateX: Float)
    fun setTranslateY(translateY: Float)

    fun render(canvas: Canvas)

}