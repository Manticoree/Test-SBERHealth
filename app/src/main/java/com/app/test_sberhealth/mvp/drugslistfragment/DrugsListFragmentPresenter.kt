package com.app.test_sberhealth.mvp.drugslistfragment

import com.app.test_sberhealth.adapter.DrugsAdapter
import com.app.test_sberhealth.base.MainApplication
import com.app.test_sberhealth.entities.DrugItem
import com.app.test_sberhealth.retrofit.RetrofitApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers


import javax.inject.Inject

class DrugsListFragmentPresenter(val view: DrugsListFragmentContract.View) :
    DrugsListFragmentContract.Presenter {

    var retrofitApi: RetrofitApi? = null
        @Inject set

    var adultList: MutableList<DrugsAdapter> = mutableListOf()
    var childList: MutableList<DrugsAdapter> = mutableListOf()

    init {
        MainApplication.applicationComponent.inject(this)
    }

    override fun getDrugs() {
        retrofitApi?.getDrugs()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : SingleObserver<List<DrugItem>> {
                override fun onSuccess(drugsList: List<DrugItem>?) {
                    for (drug in drugsList!!) {
                        if (drug.isReadyForKids) {
                            childList.add(DrugsAdapter(drug))
                        } else {
                            adultList.add(DrugsAdapter(drug))
                        }
                        view.showDrugsFragment(adultList, childList)
                    }
                }

                override fun onSubscribe(d: Disposable?) {

                }

                override fun onError(e: Throwable?) {
                    view.showErrorRepeat()
                }
            })
    }

}