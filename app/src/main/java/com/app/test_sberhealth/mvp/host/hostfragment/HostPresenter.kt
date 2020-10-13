package com.app.test_sberhealth.mvp.host.hostfragment

import java.lang.ref.WeakReference

class HostPresenter(private val view: WeakReference<HostContract.View>) :
    HostContract.Presenter {

    override fun onShowDrugFragment() {
        view.get()?.showDrugsFragment()
    }

    override fun onMoveToSearch() {
        view.get()?.moveToSearch()
    }

    override fun onTrackAnalytics() {
        view.get()?.trackAnalytics()
    }

}