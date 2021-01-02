package com.nulp.graphedu.presentation.fragments.rotation.hexagonRotation.nodels

import com.nulp.graphedu.presentation.fragments.rotation.hexagonRotation.HexagonRotationContract

data class HexagonPointViewModel(
    override val imageRes: Int,
    override val textRes: Int,
    override val onClickBlock: () -> Unit
) : HexagonRotationContract.IHexagonPointViewModel