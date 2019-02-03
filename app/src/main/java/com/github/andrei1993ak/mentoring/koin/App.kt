package com.github.andrei1993ak.mentoring.koin

import android.app.Application
import com.github.andrei1993ak.mentoring.koin.di.appModule
import com.github.andrei1993ak.mentoring.koin.di.appNavigatorModule
import com.github.andrei1993ak.mentoring.koin.di.remoteDatasourceModule
import com.github.andrei1993ak.mentoring.koin.di.userRepositoryModule
import org.koin.android.ext.android.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin(
            this, listOf(
                remoteDatasourceModule,
                userRepositoryModule,
                appNavigatorModule,
                appModule
            )
        )
    }
}