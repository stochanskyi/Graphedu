package com.nulp.graphedu.presentation.fragments.handbook.content.component

import android.os.Build
import android.text.Html
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT

class HandbookContentFormattedText(
    text: CharSequence
) : HandbookContentText(text) {
    companion object {
        fun create(text: CharSequence): HandbookContentFormattedText {
            val formatterString = HtmlCompat.fromHtml(text.toString(), FROM_HTML_MODE_COMPACT)
            return HandbookContentFormattedText(formatterString)
        }
    }
}