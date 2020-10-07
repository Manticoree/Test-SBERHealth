package com.app.test_sberhealth.mvp.showdrugsadultfragment

class ShowDrugsAdultFragmentPresenter(val view: ShowDrugsAdultFragmentContract.View) :
    ShowDrugsAdultFragmentContract.Presenter {
    override fun onInitRecView() {
        view.initRecView()
    }


}