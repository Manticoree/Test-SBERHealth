package com.app.test_sberhealth.mvp.drugslistfragment

interface DrugsListContract {

    interface View {
        fun showDrugsFragment()
        fun moveToSearch()
        fun moveToDesc(title: String)
    }

    interface Presenter {
        fun onShowDrugFragment()
        fun onMoveToSearch()
    }

}