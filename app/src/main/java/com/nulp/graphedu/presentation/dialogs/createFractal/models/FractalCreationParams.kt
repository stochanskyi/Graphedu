package com.nulp.graphedu.presentation.dialogs.createFractal.models

import com.nulp.graphedu.presentation.dialogs.createFractal.CreateFractalContract

data class FractalCreationParams(
    override val coefficient: Int,
    override val colors: List<Int>
) : CreateFractalContract.IFractalCreationResultParams