package com.app.test_sberhealth.mvp.fulldescdrugfragment

class FullDescDrugFragmentPresenter(val view: FullDescDrugFragmentContract.View) :
    FullDescDrugFragmentContract.Presenter {
    override fun onBack() {
        view.back()
    }

}