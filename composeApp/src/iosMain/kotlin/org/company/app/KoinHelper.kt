package org.company.app

import org.company.app.di.getSharedModules
import org.koin.core.context.startKoin

fun doInitKoin() {
    startKoin {
        modules(getSharedModules())
    }
}