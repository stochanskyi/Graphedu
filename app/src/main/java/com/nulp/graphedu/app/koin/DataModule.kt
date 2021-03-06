package com.nulp.graphedu.app.koin

import com.nulp.graphedu.data.formatter.AppFormatter
import com.nulp.graphedu.data.formatter.AppFormatterImpl
import com.nulp.graphedu.data.uriConverter.UriConverter
import com.nulp.graphedu.data.uriConverter.UriConverterImpl

object DataModule : KoinModule({
    single { UriConverterImpl(get()) as UriConverter }

    single { AppFormatterImpl() as AppFormatter }
})