package com.app.test_sberhealth.mvp.searchdrugsfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.app.test_sberhealth.R
import com.app.test_sberhealth.base.BaseFragment
import com.app.test_sberhealth.mvp.drugslistfragment.DrugsListFragmentView
import com.jakewharton.rxbinding4.view.clicks
import kotlinx.android.synthetic.main.fragment_search.*

class SearchDrugFragmentView : BaseFragment(), SearchDrugFragmentContract.View {

    var presenter: SearchDrugFragmentContract.Presenter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_search, container, false)

    override fun onStart() {
        super.onStart()
        if (presenter == null)
            presenter = SearchDrugFragmentPresenter(this)
        presenter?.onBack()

    }

    override fun back() {
        mbtnBack.clicks().subscribe() {
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