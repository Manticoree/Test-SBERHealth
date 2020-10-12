package com.app.test_sberhealth.mvp.show.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.app.test_sberhealth.R
import com.app.test_sberhealth.adapter.DrugsAdapter
import com.app.test_sberhealth.base.PageFragment
import com.app.test_sberhealth.entities.DrugItem
import com.app.test_sberhealth.mvp.host.hostfragment.HostFragmentDirections
import com.jakewharton.rxbinding4.view.clicks
import eu.davidea.flexibleadapter.FlexibleAdapter
import kotlinx.android.synthetic.main.fragment_showlist.*

class ShowFragment : PageFragment(),
    ShowContract.View, FlexibleAdapter.OnItemClickListener {

    private lateinit var presenter: ShowContract.Presenter
    private var drugListAdult: MutableList<DrugItem> = mutableListOf()
    private var drugListKids: MutableList<DrugItem> = mutableListOf()
    private var page: Int? = null
    private lateinit var navController: NavController

    companion object {

        private const val ARG_PAGE: String = "page"
        fun newInstance(page: Int): ShowFragment {
            return ShowFragment().apply {
                arguments = bundleOf(
                    ARG_PAGE to page
                )
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt(ARG_PAGE)?.let {
            page = it
        }
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_showlist, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        presenter = ShowPresenter(this)
        presenter.getDrugs()
    }

    override fun initRecView(initList: MutableList<DrugItem>) {
        val adapList: MutableList<DrugsAdapter> = mutableListOf()
        for (drug in initList) {
            if (page == 1) {
                if (!drug.isReadyForKids) {
                    adapList.add(DrugsAdapter(drug))
                    drugListAdult.add(drug)
                }
            } else if (page == 2) {
                if (drug.isReadyForKids) {
                    adapList.add(DrugsAdapter(drug))
                    drugListKids.add(drug)
                }
            }
        }
        showRecyclerView(adapList, rvDrugsList)
    }

    override fun showErrorRepeat() {
        visibilityElements(View.GONE, View.VISIBLE, View.VISIBLE)
    }

    override fun visibilityElements(visDrugsList: Int, visTextError: Int, visRepeat: Int) {
        rvDrugsList.visibility = visDrugsList
        mtvTextError.visibility = visTextError
        mbtnRepeat.visibility = visRepeat
    }

    override fun repeat() {
        shimmerViewContainer.visibility = View.VISIBLE
        showShimmer()
        visibilityElements(View.VISIBLE, View.GONE, View.GONE)
        presenter.getDrugs()
    }

    override fun clickRepeat() {
        mbtnRepeat.clicks().subscribe {
            repeat()
        }
    }

    override fun showShimmer() {
        shimmerViewContainer.startShimmer()
    }

    override fun hideShimmer() {
        shimmerViewContainer.stopShimmer()
        shimmerViewContainer.visibility = View.GONE
    }

    override fun onItemClick(view: View?, position: Int): Boolean {
        return if (position != RecyclerView.NO_POSITION) {
            navController.run {
                val action = HostFragmentDirections
                    .actionShowdrugToDesc()
                    .setTitle(
                        if (page == 1) {
                            drugListAdult[position].title
                        } else {
                            drugListKids[position].title
                        }
                    )
                navigate(action)
            }
            true
        } else {
            false
        }
    }

}