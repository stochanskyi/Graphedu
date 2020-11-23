package com.nulp.graphedu.app.koin

import org.koin.core.module.Module
import org.koin.dsl.ModuleDeclaration
import org.koin.dsl.module

abstract class KoinModule(
    private val moduleDeclaration: ModuleDeclaration
) {
    val module: Module = createModule()

    private fun createModule(): Module = module(moduleDeclaration = moduleDeclaration)
}