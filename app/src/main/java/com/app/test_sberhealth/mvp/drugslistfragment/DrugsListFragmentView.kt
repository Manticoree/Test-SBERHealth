package com.app.test_sberhealth.mvp.drugslistfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.app.test_sberhealth.R
import com.app.test_sberhealth.base.BaseFragment
import com.app.test_sberhealth.entities.DrugItem
import com.app.test_sberhealth.mvp.drugslistfragment.ageadapter.AgeAdapter
import com.app.test_sberhealth.mvp.errorfragment.ErrorFragmentView
import com.app.test_sberhealth.mvp.searchdrugsfragment.SearchDrugFragmentView
import com.jakewharton.rxbinding4.view.clicks
import kotlinx.android.synthetic.main.fragment_showdrug.*

class DrugsListFragmentView : BaseFragment(), DrugsListFragmentContract.View {

    var presenter: DrugsListFragmentContract.Presenter? = null
    var adapter: AgeAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_showdrug, container, false)

    override fun onStart() {
        super.onStart()
        if (presenter == null)
            presenter = DrugsListFragmentPresenter(this)
        presenter?.getDrugs()
        presenter?.onMoveToSearch()
    }

    override fun showDrugsFragment(
        listAdult: MutableList<DrugItem>,
        listChild: MutableList<DrugItem>
    ) {
        Log.i("showDrugsFragment: ", listAdult.toString())
        if (adapter == null)
            adapter = AgeAdapter(childFragmentManager, 1, listAdult, listChild)
        vpDrugsList.adapter = adapter
        tabDiffAge.setupWithViewPager(vpDrugsList)
    }

    override fun showErrorRepeat() {
        val transaction: FragmentTransaction =
            requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(
            R.id.fcvFragment,
            ErrorFragmentView()
        )
            .commit()
    }

    override fun moveToSearch() {
        mbtnSearch.clicks().subscribe() {
            val transaction: FragmentTransaction =
                requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(
                R.id.fcvFragment,
                SearchDrugFragmentView()
            )
                .addToBackStack(null)
                .commit()
        }
    }
}