package com.expert.mainactivity

import android.app.Application
import com.expert.core.di.databaseModule
import com.expert.core.di.networkModule
import com.expert.core.di.repositoryModule
import com.expert.mainactivity.di.useCaseModule
import com.expert.mainactivity.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}