package com.app.test_sberhealth.base

import android.app.Application
import com.app.test_sberhealth.di.component.AppComponent
import com.app.test_sberhealth.di.component.DaggerAppComponent
import com.app.test_sberhealth.di.modules.ApplicationModule
import com.app.test_sberhealth.di.modules.ContextModule
import com.app.test_sberhealth.di.modules.RetrofitModule

class MainApplication : Application() {

    companion object {
        lateinit var applicationComponent: AppComponent
        const val URL_DRUGS = "https://run.mocky.io"
    }

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerAppComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .contextModule(ContextModule(this))
            .retrofitModule(RetrofitModule(URL_DRUGS))
            .build()
        applicationComponent.inject(this)
    }

}