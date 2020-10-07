package com.app.test_sberhealth.mvp.drugslistactivity

import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.app.test_sberhealth.R
import com.app.test_sberhealth.base.BaseActivity
import com.app.test_sberhealth.mvp.drugslistfragment.DrugsListFragmentView

class DrugsListActivityView : BaseActivity(), DrugsListActivityContract.View {

    private var presenter: DrugsListActivityContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drugs)
        if (presenter == null)
            presenter = DrugsListActivityPresenter(this)
        presenter?.onShowDrugsListFragment()
    }

    override fun showDrugsListFragment() {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fcvFragment, DrugsListFragmentView())
        transaction.commit()
    }

    override fun onDestroy() {
        presenter = null
        super.onDestroy()
    }
}