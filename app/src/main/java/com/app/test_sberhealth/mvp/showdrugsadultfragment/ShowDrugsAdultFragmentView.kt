package com.app.test_sberhealth.mvp.showdrugsadultfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.app.test_sberhealth.R
import com.app.test_sberhealth.adapter.DrugsAdapter
import com.app.test_sberhealth.base.PageFragment
import com.app.test_sberhealth.entities.DrugItem
import com.app.test_sberhealth.mvp.fulldescdrugfragment.FullDescDrugFragmentView
import eu.davidea.flexibleadapter.FlexibleAdapter
import kotlinx.android.synthetic.main.fragment_drugslist.*

class ShowDrugsAdultFragmentView : PageFragment(),
    ShowDrugsAdultFragmentContract.View, FlexibleAdapter.OnItemClickListener {

    private var presenter: ShowDrugsAdultFragmentContract.Presenter? = null
    private var initList: MutableList<DrugsAdapter> = mutableListOf()
    private var drugList: MutableList<DrugItem> = mutableListOf()

    companion object {
        private const val ARG_PAGE: String = "ARG_PAGE"
        const val DRUGS: String = "DRUGS"
        fun newInstance(page: Int, list: ArrayList<DrugItem>): ShowDrugsAdultFragmentView {
            val args = Bundle()
            args.putInt(ARG_PAGE, page)

            args.putParcelableArrayList(DRUGS, list)
            val fragment = ShowDrugsAdultFragmentView()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        val myList: ArrayList<DrugItem> =
            args?.getParcelableArrayList<DrugItem>(DRUGS) as ArrayList<DrugItem>

        for (item in myList) {
            Log.i("ItemAdultDrugs: ", item.title)
            initList.add(DrugsAdapter(item))
            drugList.add(item)
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