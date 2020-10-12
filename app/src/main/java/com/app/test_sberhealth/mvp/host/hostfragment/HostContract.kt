package com.app.test_sberhealth.mvp.host.hostfragment

interface HostContract {

    interface View {
        fun showDrugsFragment()
        fun moveToSearch()
    }

    interface Presenter {
        fun onShowDrugFragment()
        fun onMoveToSearch()
    }

}