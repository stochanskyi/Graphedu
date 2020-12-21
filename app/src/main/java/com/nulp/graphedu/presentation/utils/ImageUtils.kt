package com.nulp.graphedu.presentation.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri

fun Uri.getBitmap(context: Context): Bitmap? {
    return context.contentResolver.openFileDescriptor(this, "r")?.use {
        BitmapFactory.decodeFileDescriptor(it.fileDescriptor)
    }
}