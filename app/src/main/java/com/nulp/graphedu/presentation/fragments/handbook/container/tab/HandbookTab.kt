package com.nulp.graphedu.presentation.fragments.handbook.container.tab

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.nulp.graphedu.presentation.fragments.handbook.content.data.HandbookContent

interface HandbookTab: Parcelable {

    @DrawableRes
    fun provideIconRes(): Int

    @StringRes
    fun provideTitleRes(): Int

    fun provideContent(): HandbookContent

}