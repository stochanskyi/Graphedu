package com.nulp.graphedu.data.colors.entity

import android.os.Parcelable

interface PixelColor: ColorTransformable, Parcelable {
    fun getFormattedString(): String
}