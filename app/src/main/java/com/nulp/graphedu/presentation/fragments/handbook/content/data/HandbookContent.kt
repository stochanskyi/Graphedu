package com.nulp.graphedu.presentation.fragments.handbook.content.data

import android.os.Parcelable
import com.nulp.graphedu.presentation.fragments.handbook.content.component.HandbookContentComponent

interface HandbookContent: Parcelable {
    fun provideComponent(): HandbookContentComponent
}