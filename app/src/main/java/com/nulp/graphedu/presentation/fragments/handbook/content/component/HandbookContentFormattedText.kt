package com.nulp.graphedu.presentation.fragments.handbook.content.component

import android.os.Build
import android.text.Html

class HandbookContentFormattedText(
    text: CharSequence
) : HandbookContentText(text) {
    companion object {
        fun create(text: CharSequence): HandbookContentFormattedText {
            val formatterString = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(text.toString(), Html.FROM_HTML_MODE_COMPACT)
            } else {
                Html.fromHtml(text.toString())
            }

            return HandbookContentFormattedText(formatterString)
        }
    }
}