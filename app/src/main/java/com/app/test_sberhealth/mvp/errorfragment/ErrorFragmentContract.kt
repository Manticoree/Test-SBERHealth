package com.app.test_sberhealth.mvp.errorfragment

interface ErrorFragmentContract {

    interface View {
        fun repeat()
    }

    interface Presenter {
        fun onRepeat()
    }

}