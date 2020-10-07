package com.app.test_sberhealth.mvp.showdrugschildfragment

interface ShowDrugsChildFragmentContract {
    interface View {
        fun initRecView()
    }

    interface Presenter {
        fun onInitRecView()
    }
}