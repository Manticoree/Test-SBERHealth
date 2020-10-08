package com.app.test_sberhealth.mvp.drugslistfragment.ageadapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.app.test_sberhealth.mvp.showdrugsadultfragment.ShowDrugsAdultFragmentView

class AgeAdapter(
    fm: FragmentManager,
    behaviour: Int
) :
    FragmentPagerAdapter(fm, behaviour) {
    companion object {
        const val PAGE_COUNT = 2
        private const val ADULT = "Взрослые"
        private const val CHILD = "Детские"
        val tabTitles: Array<String> = arrayOf(ADULT, CHILD)
    }

    override fun getItem(position: Int): Fragment {

        return if (position == 1) {
            ShowDrugsAdultFragmentView.newInstance(1)
        } else {
            ShowDrugsAdultFragmentView.newInstance(2)
        }
    }

    override fun getCount(): Int = PAGE_COUNT

    override fun getPageTitle(position: Int): CharSequence? = tabTitles[position]
}