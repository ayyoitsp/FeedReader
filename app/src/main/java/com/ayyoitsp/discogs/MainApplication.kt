/*
 * Copyright © 2021 Peter Hsu All rights reserved.
 */
package com.ayyoitsp.discogs

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val di = DI(AppConfig())
        // Start Koin, initialization DI
        startKoin {
            androidContext(this@MainApplication)
            // declare modules
            with(di) {
                modules(listOf(dataModule, useCaseModule, viewModelModule))
            }
        }
    }

}