package com.app.test_sberhealth.mvp.showdrugsadultfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.app.test_sberhealth.R
import com.app.test_sberhealth.adapter.DrugsAdapter
import com.app.test_sberhealth.base.PageFragment
import com.app.test_sberhealth.entities.DrugItem
import com.app.test_sberhealth.mvp.errorfragment.ErrorFragmentView
import com.app.test_sberhealth.mvp.fulldescdrugfragment.FullDescDrugFragmentView
import eu.davidea.flexibleadapter.FlexibleAdapter
import kotlinx.android.synthetic.main.fragment_drugslist.*

class ShowDrugsAdultFragmentView : PageFragment(),
    ShowDrugsAdultFragmentContract.View, FlexibleAdapter.OnItemClickListener {

    private var presenter: ShowDrugsAdultFragmentContract.Presenter? = null
    private var drugList: MutableList<DrugItem> = mutableListOf()
    var page: Int? = null

    companion object {
        private const val ARG_PAGE: String = "ARG_PAGE"
        fun newInstance(page: Int): ShowDrugsAdultFragmentView {
            val args = Bundle()
            args.putInt(ARG_PAGE, page)
            val fragment = ShowDrugsAdultFragmentView()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        page = args?.getInt(ARG_PAGE)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_drugslist, container, false)

    override fun onStart() {
        super.onStart()
        if (presenter == null) {
            presenter = ShowDrugsAdultFragmentPresenter(this)
        }
        presenter?.getDrugs()
    }

    override fun initRecView(initList: MutableList<DrugItem>) {
        drugList = initList
        var adapList: MutableList<DrugsAdapter> = mutableListOf()
        for (drug in initList) {
            if (page == 1) {
                if (!drug.isReadyForKids) {
                    adapList.add(DrugsAdapter(drug))
                }
            } else if (page == 2) {
                if (drug.isReadyForKids) {
                    adapList.add(DrugsAdapter(drug))
                }
            }
        }
        showRecyclerView(adapList, rvDrugsList)
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

    override fun showShimmer() {
        shimmerViewContainer.startShimmer()
    }

    override fun hideShimmer() {
        shimmerViewContainer.stopShimmer()
        shimmerViewContainer.visibility = View.GONE
    }

    override fun onItemClick(view: View?, position: Int): Boolean {

        return if (position != RecyclerView.NO_POSITION) {
            val transaction: FragmentTransaction =
                requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(
                R.id.fcvFragment,
                FullDescDrugFragmentView.newInstance(drugList[position].title)
            )
                .commit()
            true
        } else {
            false
        }
    }

}