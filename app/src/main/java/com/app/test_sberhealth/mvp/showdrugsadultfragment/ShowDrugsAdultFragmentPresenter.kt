package com.app.test_sberhealth.mvp.showdrugsadultfragment

import com.app.test_sberhealth.base.MainApplication
import com.app.test_sberhealth.entities.DrugItem
import com.app.test_sberhealth.retrofit.RetrofitApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class ShowDrugsAdultFragmentPresenter(val view: ShowDrugsAdultFragmentContract.View) :
    ShowDrugsAdultFragmentContract.Presenter {

    var retrofitApi: RetrofitApi? = null
        @Inject set


    init {
        MainApplication.applicationComponent.inject(this)
    }

    override fun getDrugs() {
        retrofitApi?.getDrugs()
            ?.subscribeOn(Schedulers.io())
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

                }

                override fun onError(e: Throwable?) {
                    view.showErrorRepeat()
                }
            })
    }

}