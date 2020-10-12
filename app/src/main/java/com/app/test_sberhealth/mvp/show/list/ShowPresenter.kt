package com.app.test_sberhealth.mvp.show.list

import com.app.test_sberhealth.base.MainApplication
import com.app.test_sberhealth.entities.DrugItem
import com.app.test_sberhealth.retrofit.RetrofitApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class ShowPresenter(val view: ShowContract.View) :
    ShowContract.Presenter {

    @Inject
    lateinit var retrofitApi: RetrofitApi


    var disGetDrug: Disposable? = null

    init {
        MainApplication.applicationComponent.inject(this)
    }

    override fun getDrugs() {
        retrofitApi.getDrugs()
            .subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnSubscribe {
                view.showShimmer()
            }
            ?.doAfterSuccess {
                view.hideShimmer()
            }
            ?.subscribe(object : SingleObserver<List<DrugItem>> {
                override fun onSuccess(drugsList: List<DrugItem>?) {
                    view.initRecView(drugsList as MutableList<DrugItem>)
                }

                override fun onSubscribe(d: Disposable?) {
                    disGetDrug = d
                }

                override fun onError(e: Throwable?) {
                    view.hideShimmer()
                    view.showErrorRepeat()
                    view.clickRepeat()
                    disGetDrug?.dispose()
                }
            })
    }

}