package com.app.test_sberhealth.mvp.drugslistfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.app.test_sberhealth.R
import com.app.test_sberhealth.base.BaseFragment
import com.app.test_sberhealth.mvp.drugslistfragment.ageadapter.AgeAdapter
import com.app.test_sberhealth.mvp.drugslistfragment.callback.ClickDrugListener
import com.jakewharton.rxbinding4.view.clicks
import kotlinx.android.synthetic.main.fragment_showdrug.*

class DrugsListFragmentView : BaseFragment(), DrugsListFragmentContract.View {

    var presenter: DrugsListFragmentContract.Presenter? = null
    var adapter: AgeAdapter? = null
    lateinit var navController: NavController

    private var drugTitleCallback: ClickDrugListener? = object : ClickDrugListener {
        override fun showFullDesc(title: String) {
            Log.i("titleMessage: ", title)
            val action = DrugsListFragmentViewDirections
                .actionShowdrugToDesc()
                .setTitle(title)
            navController.run {
                navigate(action)
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_showdrug, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun onStart() {
        super.onStart()
        Log.i("onFragmentStart: ", "")
        if (presenter == null)
            presenter = DrugsListFragmentPresenter(this)
        presenter?.onShowDrugFragment()
        presenter?.onMoveToSearch()
    }

    override fun showDrugsFragment() {

        if (adapter == null)
            adapter = AgeAdapter(childFragmentManager, 1, drugTitleCallback)

        tabDiffAge.setupWithViewPager(vpDrugsList)
        vpDrugsList.adapter = adapter

    }

    override fun moveToSearch() {
        mbtnSearch.clicks()
            .subscribe {
                navController.navigate(R.id.action_showdrug_to_search)
            }
    }

    override fun onStop() {
        drugTitleCallback = null
        presenter = null
        adapter = null
        super.onStop()
    }
}