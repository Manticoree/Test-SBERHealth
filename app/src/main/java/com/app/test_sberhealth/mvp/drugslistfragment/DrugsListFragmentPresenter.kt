package com.app.test_sberhealth.mvp.drugslistfragment

class DrugsListFragmentPresenter(val view: DrugsListFragmentContract.View) :
    DrugsListFragmentContract.Presenter {

    override fun onShowDrugFragment() {
        view.showDrugsFragment()
    }

    override fun onMoveToSearch() {
        view.moveToSearch()
    }

}