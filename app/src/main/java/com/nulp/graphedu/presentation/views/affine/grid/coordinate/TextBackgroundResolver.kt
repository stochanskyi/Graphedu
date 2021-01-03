package com.nulp.graphedu.presentation.views.affine.grid.coordinate

import android.graphics.Paint
import android.graphics.RectF
import android.text.TextPaint

interface TextBackgroundResolver {

    val align: Paint.Align

    fun resolve(x: Float, y: Float, paint: TextPaint, text: String): RectF

}