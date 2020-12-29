package com.nulp.graphedu.presentation.fragments.imageBoundsSelector

import android.graphics.Bitmap

interface ImageBoundsSelectionListener {

    fun provideBoundsSelectionImage(): Bitmap

    fun handleImageBoundsSelected(bounds: ImageBounds)

}