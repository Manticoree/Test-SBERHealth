package com.app.test_sberhealth.mvp.drugslistfragment

interface DrugsListFragmentContract {

    interface View {
        fun showDrugsFragment()
        fun moveToSearch()
    }

    interface Presenter {
        fun onShowDrugFragment()
        fun onMoveToSearch()
    }

}