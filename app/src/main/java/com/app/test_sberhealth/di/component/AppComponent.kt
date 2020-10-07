package com.app.test_sberhealth.di.component

import android.app.Application
import android.content.Context
import com.app.test_sberhealth.base.MainApplication
import com.app.test_sberhealth.di.ApplicationContext
import com.app.test_sberhealth.di.modules.ApplicationModule
import com.app.test_sberhealth.di.modules.ContextModule
import com.app.test_sberhealth.di.modules.RetrofitModule
import com.app.test_sberhealth.mvp.showdrugsadultfragment.ShowDrugsAdultFragmentPresenter
import com.app.test_sberhealth.mvp.showdrugschildfragment.ShowDrugsChildFragmentPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RetrofitModule::class,
        ContextModule::class,
        ApplicationModule::class
    ]
)
interface AppComponent {

    @ApplicationContext
    fun context(): Context

    fun application(): Application

    fun inject(mainApplication: MainApplication)

    fun inject(showDrugsAdultFragmentPresenter: ShowDrugsAdultFragmentPresenter)

    fun inject(showDrugsChildFragmentPresenter: ShowDrugsChildFragmentPresenter)

}