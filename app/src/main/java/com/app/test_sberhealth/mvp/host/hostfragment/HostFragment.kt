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
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_host.*
import java.lang.ref.WeakReference

class HostFragment : BaseFragment(), HostContract.View {

    private lateinit var presenter: HostContract.Presenter
    private lateinit var adapter: AgeAdapter
    private lateinit var navController: NavController
    private var disBtnSearch: Disposable? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_host, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        presenter = HostPresenter(WeakReference(this))
        presenter.onShowDrugFragment()
        presenter.onMoveToSearch()
    }

    override fun showDrugsFragment() {
        adapter =
            AgeAdapter(
                childFragmentManager,
                1
            )
        tabDiffAge.setupWithViewPager(vpDrugsList)
        vpDrugsList.adapter = adapter
    }

    override fun moveToSearch() {
        disBtnSearch = mbtnSearch.clicks()
            .subscribe {
                navController.navigate(R.id.action_showdrug_to_search)
            }
    }

    override fun onDetach() {
        disBtnSearch?.dispose()
        super.onDetach()
    }

}