package com.nulp.graphedu.app.koin

import com.nulp.graphedu.data.uriConverter.UriConverter
import com.nulp.graphedu.data.uriConverter.UriConverterImpl

class DataModule : KoinModule({
    single { UriConverterImpl(get()) as UriConverter }
})