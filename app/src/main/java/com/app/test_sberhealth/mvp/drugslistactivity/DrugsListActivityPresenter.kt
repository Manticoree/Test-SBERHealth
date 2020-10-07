package com.app.test_sberhealth.mvp.drugslistactivity

class DrugsListActivityPresenter(private val view: DrugsListActivityContract.View) :
    DrugsListActivityContract.Presenter {

    override fun onShowDrugsListFragment() {
        view.showDrugsListFragment()
    }

}