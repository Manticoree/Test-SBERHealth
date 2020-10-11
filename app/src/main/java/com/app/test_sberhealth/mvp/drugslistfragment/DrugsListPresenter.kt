package com.app.test_sberhealth.mvp.drugslistfragment

class DrugsListPresenter(val view: DrugsListContract.View) :
    DrugsListContract.Presenter {

    override fun onShowDrugFragment() {
        view.showDrugsFragment()
    }

    override fun onMoveToSearch() {
        view.moveToSearch()
    }

}