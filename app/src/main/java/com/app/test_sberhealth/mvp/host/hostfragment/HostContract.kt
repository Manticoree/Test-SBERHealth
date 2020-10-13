package com.app.test_sberhealth.mvp.host.hostfragment

interface HostContract {

    interface View {
        fun showDrugsFragment()
        fun moveToSearch()
        fun trackAnalytics()
    }

    interface Presenter {
        fun onShowDrugFragment()
        fun onMoveToSearch()
        fun onTrackAnalytics()
    }

}