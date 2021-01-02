package com.nulp.graphedu.presentation.fragments.fractals.imageBoundsSelector

import android.graphics.Bitmap

interface ImageBoundsSelectionListener {

    fun provideBoundsSelectionImage(): Bitmap

    fun handleImageBoundsSelected(bounds: ImageBounds)

}