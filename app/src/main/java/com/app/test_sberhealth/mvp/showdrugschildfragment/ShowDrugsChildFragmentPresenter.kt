package com.app.test_sberhealth.mvp.showdrugschildfragment

class ShowDrugsChildFragmentPresenter(val view: ShowDrugsChildFragmentContract.View) :
    ShowDrugsChildFragmentContract.Presenter {

    override fun onInitRecView() {
        view.initRecView()
    }

}