package com.nulp.graphedu.data.uriConverter

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import com.nulp.graphedu.data.observeOnUI
import com.nulp.graphedu.data.onApiThread
import com.nulp.graphedu.presentation.utils.getBitmap
import io.reactivex.rxjava3.core.Single
import java.lang.IllegalStateException

class UriConverterImpl(
    private val context: Context
): UriConverter {
    override fun toBitmap(uri: Uri): Single<Bitmap> {
        return Single.fromCallable {
            uri.getBitmap(context) ?: throw IllegalStateException("Cannot get bitmap")
        }
            .onApiThread()
            .observeOnUI()
    }
}