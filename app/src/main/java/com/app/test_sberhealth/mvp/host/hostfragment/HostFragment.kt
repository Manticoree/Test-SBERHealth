package com.app.test_sberhealth.mvp.host.hostfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.app.test_sberhealth.R
import com.app.test_sberhealth.base.BaseFragment
import com.app.test_sberhealth.mvp.host.hostfragment.adapter.age.AgeAdapter
import com.jakewharton.rxbinding4.view.clicks
import kotlinx.android.synthetic.main.fragment_host.*

class HostFragment : BaseFragment(), HostContract.View {

    private var presenter: HostContract.Presenter? = null
    private var adapter: AgeAdapter? = null
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_host, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        if (presenter == null)
            presenter = HostPresenter(this)
        presenter?.onShowDrugFragment()
        presenter?.onMoveToSearch()
    }

    override fun showDrugsFragment() {
        if (adapter == null)
            adapter =
                AgeAdapter(
                    childFragmentManager,
                    1
                )
        tabDiffAge.setupWithViewPager(vpDrugsList)
        vpDrugsList.adapter = adapter
    }

    override fun moveToSearch() {
        mbtnSearch.clicks()
            .subscribe {
                navController.navigate(R.id.action_showdrug_to_search)
            }
    }

    override fun moveToDesc(title: String) {
        val action = HostFragmentDirections
            .actionShowdrugToDesc()
            .setTitle(title)
        navController.run {
            navigate(action)
        }
    }

    override fun onDetach() {
        presenter = null
        adapter = null
        super.onDetach()
    }

}