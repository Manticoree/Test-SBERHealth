package com.app.test_sberhealth.mvp.host.hostfragment

class HostPresenter(val view: HostContract.View) :
    HostContract.Presenter {

    override fun onShowDrugFragment() {
        view.showDrugsFragment()
    }

    override fun onMoveToSearch() {
        view.moveToSearch()
    }

}