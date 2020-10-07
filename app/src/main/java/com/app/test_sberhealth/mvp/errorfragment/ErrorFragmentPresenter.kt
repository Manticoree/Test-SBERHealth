package com.app.test_sberhealth.mvp.errorfragment

class ErrorFragmentPresenter(val view: ErrorFragmentContract.View) :
    ErrorFragmentContract.Presenter {

    override fun onRepeat() {
        view.repeat()
    }

}