package com.nulp.graphedu.data.palette

import com.nulp.graphedu.data.colors.container.ColorsContainer

interface PaletteGenerator {
    fun generate(): ColorsContainer
}