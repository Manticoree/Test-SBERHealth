package com.app.test_sberhealth.mvp.searchdrugsfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.test_sberhealth.R
import com.app.test_sberhealth.base.BaseFragment

class SearchDrugFragment : BaseFragment(), SearchDrugContract.View {

    var presenter: SearchDrugContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_search, container, false)

    override fun onStart() {
        super.onStart()
        if (presenter == null)
            presenter = SearchDrugPresenter(this)

    }

}