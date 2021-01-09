package com.nulp.graphedu.presentation.fragments.handbook.container.tab

import com.nulp.graphedu.R
import com.nulp.graphedu.presentation.fragments.handbook.content.data.HandbookContent
import com.nulp.graphedu.presentation.fragments.handbook.content.data.HandbookEmpty
import kotlinx.android.parcel.Parcelize

@Parcelize
object HandbookTabFractals: HandbookTab {

    override fun provideIconRes(): Int = R.drawable.ic_fractal

    override fun provideTitleRes(): Int = R.string.navigation_item_fractal

    override fun provideContent(): HandbookContent = HandbookEmpty()

}