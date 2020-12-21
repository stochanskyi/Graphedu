package com.nulp.graphedu.data.uriConverter

import android.graphics.Bitmap
import android.net.Uri
import io.reactivex.rxjava3.core.Single

interface UriConverter {
    fun toBitmap(uri: Uri): Single<Bitmap>
}