package com.template.ym

import android.app.Application
import com.template.core.dagger.MainModule

class MyApp : Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .mainModule(MainModule(this))
            .build()
    }
}