package com.ozanyazici.koincryptocrazy

import android.app.Application
import com.ozanyazici.koincryptocrazy.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import com.ozanyazici.koincryptocrazy.di.anotherModule

// Koin kotlin dependcy injection içindir. Koin runtime da çalışır hilt compile time da çalışır.
// Bu durum koin in performasının kötü olduğu anlamına gelmez kullanımı hilt e göre daha kolaydır.

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(appModule, anotherModule)
        }
    }
}