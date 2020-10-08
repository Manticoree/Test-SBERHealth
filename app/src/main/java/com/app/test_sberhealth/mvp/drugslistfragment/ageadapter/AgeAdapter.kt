package com.app.test_sberhealth.mvp.drugslistfragment.ageadapter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.app.test_sberhealth.entities.DrugItem
import com.app.test_sberhealth.mvp.showdrugsadultfragment.ShowDrugsAdultFragmentView

class AgeAdapter(
    fm: FragmentManager,
    behaviour: Int,
    private val listAdult: MutableList<DrugItem>,
    private val listChild: MutableList<DrugItem>
) :
    FragmentPagerAdapter(fm, behaviour) {
    companion object {
        const val PAGE_COUNT = 2
        private const val ADULT = "Взрослые"
        private const val CHILD = "Детские"
        val tabTitles: Array<String> = arrayOf(ADULT, CHILD)
    }

    override fun getItem(position: Int): Fragment {
        Log.i("showDrugsInitAdap", listAdult.toString())
        return if (position == 1) {
            ShowDrugsAdultFragmentView.newInstance(1, listAdult as ArrayList<DrugItem>)
        } else {
            ShowDrugsAdultFragmentView.newInstance(2, listChild as ArrayList<DrugItem>)
        }
    }

    override fun getCount(): Int = PAGE_COUNT

    override fun getPageTitle(position: Int): CharSequence? = tabTitles[position]
}