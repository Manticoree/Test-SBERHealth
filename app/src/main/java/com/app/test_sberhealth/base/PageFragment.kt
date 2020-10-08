package com.app.test_sberhealth.base

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.test_sberhealth.adapter.DrugsAdapter
import eu.davidea.flexibleadapter.FlexibleAdapter

open class PageFragment : BaseFragment() {

    private var adapter: FlexibleAdapter<DrugsAdapter>? = null

    fun showRecyclerView(initList: List<DrugsAdapter>?, rvDrugs: RecyclerView) {
        rvDrugs.setHasFixedSize(true)
        val manager: RecyclerView.LayoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )
        rvDrugs.layoutManager = manager
        adapter = FlexibleAdapter(initList)
        adapter?.addListener(this)
        rvDrugs.adapter = adapter
    }

}