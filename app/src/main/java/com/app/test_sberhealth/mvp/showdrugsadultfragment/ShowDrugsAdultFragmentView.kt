package com.app.test_sberhealth.mvp.showdrugsadultfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.test_sberhealth.R
import com.app.test_sberhealth.adapter.DrugsAdapter
import com.app.test_sberhealth.base.PageFragment
import kotlinx.android.synthetic.main.fragment_drugslist.*

class ShowDrugsAdultFragmentView(var initList: MutableList<DrugsAdapter>) : PageFragment(),
    ShowDrugsAdultFragmentContract.View {

    private var presenter: ShowDrugsAdultFragmentContract.Presenter? = null

    companion object {
        private const val ARG_PAGE: String = "ARG_PAGE"
        fun newInstance(page: Int, list: MutableList<DrugsAdapter>): ShowDrugsAdultFragmentView {
            val args = Bundle()
            args.putInt(ARG_PAGE, page)
            val fragment = ShowDrugsAdultFragmentView(list)
            fragment.arguments = args
            return fragment
        }
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
        presenter?.onInitRecView()
    }

    override fun initRecView() {
        showRecyclerView(initList, rvDrugsList)
    }

}