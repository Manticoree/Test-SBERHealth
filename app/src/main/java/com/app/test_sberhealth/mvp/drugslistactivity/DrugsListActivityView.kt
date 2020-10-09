package com.app.test_sberhealth.mvp.drugslistactivity

import android.os.Bundle
import android.util.Log
import com.app.test_sberhealth.R
import com.app.test_sberhealth.base.BaseActivity

class DrugsListActivityView : BaseActivity(), DrugsListActivityContract.View {

    private var presenter: DrugsListActivityContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("onBugCreateActivity: ", "")
        setContentView(R.layout.activity_drugs)
        if (presenter == null)
            presenter = DrugsListActivityPresenter(this)
    }

    override fun onStart() {
        super.onStart()
        Log.i("onBugStartActivity: ", "")
    }

    override fun onResume() {
        super.onResume()
        Log.i("onBugResumeActivity: ", "")
    }

    override fun onPause() {
        Log.i("onBugPauseActivity: ", "")
        super.onPause()

    }

    override fun onStop() {
        Log.i("onBugStopActivity: ", "")
        super.onStop()

    }

    override fun onDestroy() {
        Log.i("onBugDestroyActivity: ", "")
        presenter = null
        super.onDestroy()
    }

}