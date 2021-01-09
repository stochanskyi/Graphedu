package com.nulp.graphedu.presentation.fragments.handbook.container.tab

import com.nulp.graphedu.R
import com.nulp.graphedu.presentation.fragments.handbook.content.data.HandbookContent
import com.nulp.graphedu.presentation.fragments.handbook.content.data.HandbookEmpty
import kotlinx.android.parcel.Parcelize

@Parcelize
object HandbookTabColors: HandbookTab {

    override fun provideIconRes(): Int = R.drawable.ic_colors

    override fun provideTitleRes(): Int = R.string.navigation_item_colors

    override fun provideContent(): HandbookContent = HandbookEmpty()
    
}