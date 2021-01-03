package com.nulp.graphedu.app.koin

import com.nulp.graphedu.data.formatter.AppFormatter
import com.nulp.graphedu.data.formatter.AppFormatterImpl
import com.nulp.graphedu.data.uriConverter.UriConverter
import com.nulp.graphedu.data.uriConverter.UriConverterImpl
import com.nulp.graphedu.hexagonRotation.hexagon.generator.HexagonGenerator
import com.nulp.graphedu.hexagonRotation.hexagon.generator.HexagonGeneratorImpl

object DataModule : KoinModule({
    single { UriConverterImpl(get()) as UriConverter }

    single { AppFormatterImpl() as AppFormatter }

    single { HexagonGeneratorImpl() as HexagonGenerator }

})