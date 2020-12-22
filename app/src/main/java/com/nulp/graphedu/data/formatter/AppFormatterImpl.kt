package com.nulp.graphedu.data.formatter

class AppFormatterImpl : AppFormatter {

    override fun formatColorHex(color: Int, lowerCase: Boolean): String {
        return String.format("#%06X", (0xFFFFFF and color))
    }

}