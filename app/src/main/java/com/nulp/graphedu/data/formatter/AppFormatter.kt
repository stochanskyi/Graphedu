package com.nulp.graphedu.data.formatter

interface AppFormatter {
    fun formatColorHex(color: Int, lowerCase: Boolean = false): String
}