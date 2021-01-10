package com.nulp.graphedu.presentation.fragments.handbook.content.data

import com.nulp.graphedu.R
import com.nulp.graphedu.presentation.fragments.handbook.content.component.*
import kotlinx.android.parcel.Parcelize

@Parcelize
class HandbookContentTest : HandbookContent {

    override fun provideComponent(): HandbookContentComponent {
        return HandbookLinearContainer(
            HandbookContentTitle("Some test title"),
            HandbookContentText("With some message that can be very very very very very very very very very very long and also\nmulti\nline"),
            HandbookContentImage(R.drawable.ic_colors, true),
            HandbookContentImage(R.drawable.logo_260px, true),
            HandbookContentFormula("x = \\frac{-b \\pm \\sqrt{b^2-4ac}}{2a}")
        )
    }
}