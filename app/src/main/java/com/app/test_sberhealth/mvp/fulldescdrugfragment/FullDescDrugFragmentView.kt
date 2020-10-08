package com.app.test_sberhealth.mvp.fulldescdrugfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.app.test_sberhealth.R
import com.app.test_sberhealth.base.BaseFragment
import com.app.test_sberhealth.mvp.drugslistfragment.DrugsListFragmentView
import com.jakewharton.rxbinding4.view.clicks
import kotlinx.android.synthetic.main.fragment_fulldescriptiondrug.*
import kotlinx.android.synthetic.main.fragment_search.mbtnBack

class FullDescDrugFragmentView : BaseFragment(), FullDescDrugFragmentContract.View {

    var presenter: FullDescDrugFragmentContract.Presenter? = null
    private var title: String? = null

    companion object {
        const val TITLE: String = "TITLE"
        fun newInstance(title: String): FullDescDrugFragmentView {
            var args = Bundle()
            args.putString(TITLE, title)
            val fragment = FullDescDrugFragmentView()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = arguments?.getString(TITLE)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_fulldescriptiondrug, container, false)

    override fun onStart() {
        super.onStart()
        if (presenter == null)
            presenter = FullDescDrugFragmentPresenter(this)
        presenter?.onBack()
        mtvTitleDrugs.text = title

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