package com.app.test_sberhealth.mvp.drugslistfragment


import android.util.Log
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

    var adultList: MutableList<DrugItem> = mutableListOf()
    var childList: MutableList<DrugItem> = mutableListOf()

    init {
        MainApplication.applicationComponent.inject(this)
    }

    override fun getDrugs() {
        retrofitApi?.getDrugs()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doAfterSuccess {
                view.showDrugsFragment(adultList, childList)
            }
            ?.subscribe(object : SingleObserver<List<DrugItem>> {
                override fun onSuccess(drugsList: List<DrugItem>?) {
                    for (drug in drugsList!!) {
                        if (drug.isReadyForKids) {
                            childList.add(drug)
                        } else {
                            adultList.add(drug)
                        }
                    }
                    Log.i("drugListInit: ", adultList.toString())

                }

                override fun onSubscribe(d: Disposable?) {

                }

                override fun onError(e: Throwable?) {
                    view.showErrorRepeat()
                }
            })
    }

    override fun onMoveToSearch() {
        view.moveToSearch()
    }

}