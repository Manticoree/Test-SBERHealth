package com.app.test_sberhealth.mvp.drugslistactivity

import android.os.Bundle
import com.app.test_sberhealth.R
import com.app.test_sberhealth.base.BaseActivity

class DrugsListActivityView : BaseActivity(), DrugsListActivityContract.View {

    private var presenter: DrugsListActivityContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drugs)
        if (presenter == null)
            presenter = DrugsListActivityPresenter(this)
    }
}