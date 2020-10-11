package com.app.test_sberhealth.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.app.test_sberhealth.R
import com.app.test_sberhealth.entities.DrugItem
import com.bumptech.glide.Glide
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import eu.davidea.flexibleadapter.items.IFlexible
import eu.davidea.viewholders.FlexibleViewHolder
import kotlinx.android.synthetic.main.item_drug.view.*

class DrugsAdapter(private val drugItem: DrugItem?) :
    AbstractFlexibleItem<DrugsAdapter.DrugHolder>() {

    override fun bindViewHolder(
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>?,
        holder: DrugHolder?,
        position: Int,
        payloads: MutableList<Any>?
    ) {
        holder?.bind(drugItem)
    }

    override fun equals(other: Any?): Boolean = true

    override fun createViewHolder(
        view: View,
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>?
    ): DrugHolder = DrugHolder(view, adapter)

    override fun getLayoutRes(): Int = R.layout.item_drug

    override fun hashCode(): Int {
        return drugItem?.hashCode() ?: 0
    }

    inner class DrugHolder(private val view: View, adapter: FlexibleAdapter<out IFlexible<*>>?) :
        FlexibleViewHolder(view, adapter) {

        fun bind(item: DrugItem?) {
            itemView.mtvDrugsDescription.text = item?.title
            Glide.with(view)
                .load(item?.icon)
                .into(itemView.sivDrugsImage)
        }
    }

}