package com.app.test_sberhealth.mvp.drugslistactivity

interface DrugsListActivityContract {

    interface View {
        fun showDrugsListFragment()
    }

    interface Presenter {
        fun onShowDrugsListFragment()
    }

}