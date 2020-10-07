package com.app.test_sberhealth.mvp.errorfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.app.test_sberhealth.R
import com.app.test_sberhealth.base.BaseFragment
import com.app.test_sberhealth.mvp.drugslistfragment.DrugsListFragmentView
import com.jakewharton.rxbinding4.view.clicks
import kotlinx.android.synthetic.main.fragment_error.*

class ErrorFragmentView : BaseFragment(), ErrorFragmentContract.View {

    var presenter: ErrorFragmentContract.Presenter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_error, container, false)

    override fun onStart() {
        super.onStart()
        if (presenter == null)
            presenter = ErrorFragmentPresenter(this)
        presenter?.onRepeat()
    }

    override fun repeat() {
        mbtnRepeat.clicks().subscribe() {
            val transaction: FragmentTransaction =
                requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(
                R.id.fcvFragment,
                DrugsListFragmentView()
            )
                .commit()
        }
    }


}